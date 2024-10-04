package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hfdy.sky.pojo.dto.GoodsSalesDTO;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.pojo.entity.User;
import com.hfdy.sky.pojo.vo.*;
import com.hfdy.sky.server.mapper.OrderMapper;
import com.hfdy.sky.server.mapper.UserMapper;
import com.hfdy.sky.server.service.ReportService;
import com.hfdy.sky.server.service.WorkSpaceService;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/3 14:52
 */
@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WorkSpaceService workSpaceService;

    /**
     * 根据起始和结束日期获取日期范围列表
     *
     * @param begin
     * @param end
     * @return
     */
    private List<LocalDate> getDateListByBeginAndEndDate(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = new ArrayList<>();
        while (!begin.equals(end)) {
            localDateList.add(begin);
            begin = begin.plusDays(1);
        }
        return localDateList;
    }

    @Override
    public TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = getDateListByBeginAndEndDate(begin, end);
        //
        List<Double> turnoverList = localDateList.stream().map(date -> {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.COMPLETED)
                    .between(Orders::getCheckoutTime, beginTime, endTime);
            List<Orders> ordersList = orderMapper.selectList(ordersLambdaQueryWrapper);
            return ordersList.stream().map(Orders::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        }).toList();
        return TurnoverReportVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .turnoverList(StringUtils.join(turnoverList, ","))
                .build();
    }

    @Override
    public UserReportVO userStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = getDateListByBeginAndEndDate(begin, end);
        List<Integer> newUserList = new ArrayList<>(); // 新增用户数
        List<Integer> totalUserList = new ArrayList<>(); // 总用户数

        localDateList.forEach(date -> {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.between(User::getCreateTime, beginTime, endTime);
            newUserList.add(userMapper.selectCount(userLambdaQueryWrapper).intValue());
            userLambdaQueryWrapper.clear();
            userLambdaQueryWrapper.le(User::getCreateTime, endTime);
            totalUserList.add(userMapper.selectCount(userLambdaQueryWrapper).intValue());
        });
        return UserReportVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .newUserList(StringUtils.join(newUserList, ","))
                .totalUserList(StringUtils.join(totalUserList, ","))
                .build();
    }

    @Override
    public OrderReportVO orderStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = getDateListByBeginAndEndDate(begin, end);
        List<Integer> orderCountList = new ArrayList<>(); // 每日订单数
        List<Integer> validOrderCountList = new ArrayList<>(); // 每日有效订单数

        localDateList.forEach(date -> {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ordersLambdaQueryWrapper.between(Orders::getOrderTime, beginTime, endTime);
            orderCountList.add(orderMapper.selectCount(ordersLambdaQueryWrapper).intValue());
            ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.COMPLETED);
            validOrderCountList.add(orderMapper.selectCount(ordersLambdaQueryWrapper).intValue());
        });
        int totalOrderCount = orderCountList.stream().reduce(0, Integer::sum); // 订单总数
        int validOrderCount = validOrderCountList.stream().reduce(0, Integer::sum); // 有效订单总数
        double orderCompletionRate = totalOrderCount == 0 ? 0d : ((double) validOrderCount / totalOrderCount); // 订单完成率

        return OrderReportVO.builder()
                .dateList(StringUtils.join(localDateList, ","))
                .orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ","))
                .totalOrderCount(totalOrderCount)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .build();
    }


    @Override
    public SalesTop10ReportVO getTop10Statistics(LocalDate begin, LocalDate end) {
        LocalDateTime beginTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(end, LocalTime.MAX);
        List<GoodsSalesDTO> goodsSalesDTOList = orderMapper.getSalesTop10(beginTime, endTime);
        String nameList = StringUtils.join(goodsSalesDTOList.stream().map(GoodsSalesDTO::getName).toList(), ",");
        String numberList = StringUtils.join(goodsSalesDTOList.stream().map(GoodsSalesDTO::getNumber).toList(), ",");
        return SalesTop10ReportVO.builder()
                .nameList(nameList)
                .numberList(numberList)
                .build();
    }

    @Override
    public void exportBusinessData(HttpServletResponse httpServletResponse) {
        LocalDate begin = LocalDate.now().minusDays(30);
        LocalDate end = LocalDate.now().minusDays(1);
        BusinessDataVO businessData = workSpaceService.getBusinessData(LocalDateTime.of(begin, LocalTime.MIN), LocalDateTime.of(end, LocalTime.MAX));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/运营数据报表模板.xlsx");
        try {
            XSSFWorkbook excel = null;
            if (inputStream == null) {
                throw new RuntimeException("未找到模板或模板打开失败");
            }
            excel = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = excel.getSheet("Sheet1");
            sheet.getRow(1).getCell(1).setCellValue(begin + "至" + end);
            // 第四行
            XSSFRow row = sheet.getRow(3);
            // 单元格
            row.getCell(2).setCellValue(businessData.getTurnover());
            row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
            row.getCell(6).setCellValue(businessData.getNewUsers());
            row = sheet.getRow(4);
            row.getCell(2).setCellValue(businessData.getValidOrderCount());
            row.getCell(4).setCellValue(businessData.getUnitPrice());
            for (int i = 0; i < 30; i++) {
                LocalDate date = begin.plusDays(i);
                businessData = workSpaceService.getBusinessData(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
                row = sheet.getRow(7 + i);
                row.getCell(1).setCellValue(date.toString());
                row.getCell(2).setCellValue(businessData.getTurnover());
                row.getCell(3).setCellValue(businessData.getValidOrderCount());
                row.getCell(4).setCellValue(businessData.getOrderCompletionRate());
                row.getCell(5).setCellValue(businessData.getUnitPrice());
                row.getCell(6).setCellValue(businessData.getNewUsers());
            }
            // 输出
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            excel.write(outputStream);
            // 关闭
            outputStream.flush();
            outputStream.close();
            excel.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
