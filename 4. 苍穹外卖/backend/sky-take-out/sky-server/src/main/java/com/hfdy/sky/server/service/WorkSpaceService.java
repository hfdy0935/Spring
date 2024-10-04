package com.hfdy.sky.server.service;

import com.hfdy.sky.pojo.vo.BusinessDataVO;
import com.hfdy.sky.pojo.vo.DishOverViewVO;
import com.hfdy.sky.pojo.vo.OrderOverViewVO;
import com.hfdy.sky.pojo.vo.SetmealOverViewVO;

import java.time.LocalDateTime;

/**
 * @author hf-dy
 * @date 2024/10/3 17:50
 */

public interface WorkSpaceService {

    /**
     * 获取一定时间段的统计
     *
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * 今日统计，不穿就是今天一天的，后面的类似
     *
     * @return
     */
    BusinessDataVO getBusinessData();


    /**
     * 订单总览
     *
     * @return
     */
    OrderOverViewVO getOrdersOverview();

    /**
     * 菜品总览
     *
     * @return
     */
    DishOverViewVO getOverviewDishes();


    /**
     * 套餐总览
     *
     * @return
     */
    SetmealOverViewVO getOverviewSetmeals();
}
