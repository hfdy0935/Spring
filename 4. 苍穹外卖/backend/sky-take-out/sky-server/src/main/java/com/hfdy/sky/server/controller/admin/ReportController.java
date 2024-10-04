package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.vo.OrderReportVO;
import com.hfdy.sky.pojo.vo.SalesTop10ReportVO;
import com.hfdy.sky.pojo.vo.TurnoverReportVO;
import com.hfdy.sky.pojo.vo.UserReportVO;
import com.hfdy.sky.server.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author hf-dy
 * @date 2024/10/3 14:50
 */
@RestController
@RequestMapping("/admin/report")
@Slf4j
@Api("管理端统计报表接口")
public class ReportController {
    @Resource
    private ReportService reportService;

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额")
    public ApiResult<TurnoverReportVO> turnoverStatistics(LocalDate begin, LocalDate end) {
        log.info("查询营业额：{} - {}", begin, end);
        return ApiResult.success(reportService.turnoverStatistics(begin, end));
    }

    @GetMapping("/userStatistics")
    @ApiOperation("用户数量")
    public ApiResult<UserReportVO> userStatistics(LocalDate begin, LocalDate end) {
        log.info("查询用户数量：{} - {}", begin, end);
        return ApiResult.success(reportService.userStatistics(begin, end));
    }

    @GetMapping("/ordersStatistics")
    @ApiOperation("订单数量")
    public ApiResult<OrderReportVO> orderStatistics(LocalDate begin, LocalDate end) {
        log.info("查询订单数量：{} - {}", begin, end);
        return ApiResult.success(reportService.orderStatistics(begin, end));
    }

    @GetMapping("/top10")
    @ApiOperation("销量前100")
    public ApiResult<SalesTop10ReportVO> getTop10Statistics(LocalDate begin, LocalDate end) {
        log.info("查询订单数量：{} - {}", begin, end);
        return ApiResult.success(reportService.getTop10Statistics(begin, end));
    }

    @GetMapping("/export")
    @ApiOperation("导出数据")
    public ApiResult<Void> exportBusinessData(HttpServletResponse httpServletResponse) {
        log.info("导出数据");
        reportService.exportBusinessData(httpServletResponse);
        return ApiResult.success();
    }
}
