package com.hfdy.sky.server.service;

import com.hfdy.sky.pojo.vo.OrderReportVO;
import com.hfdy.sky.pojo.vo.SalesTop10ReportVO;
import com.hfdy.sky.pojo.vo.TurnoverReportVO;
import com.hfdy.sky.pojo.vo.UserReportVO;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

/**
 * @author hf-dy
 * @date 2024/10/3 14:51
 */

public interface ReportService {

    /**
     * 获取报表内容
     *
     * @param begin
     * @param end
     * @return
     */
    TurnoverReportVO turnoverStatistics(LocalDate begin, LocalDate end);

    /**
     * 用户统计
     *
     * @param begin
     * @param end
     * @return
     */
    UserReportVO userStatistics(LocalDate begin, LocalDate end);


    /**
     * 订单统计
     *
     * @param begin
     * @param end
     * @return
     */
    OrderReportVO orderStatistics(LocalDate begin, LocalDate end);


    /**
     * 销量前100统计
     *
     * @param begin
     * @param end
     * @return
     */
    SalesTop10ReportVO getTop10Statistics(LocalDate begin, LocalDate end);

    /**
     * 导出近30天的运营数据报表
     *
     * @param httpServletResponse
     */
    void exportBusinessData(HttpServletResponse httpServletResponse);
}
