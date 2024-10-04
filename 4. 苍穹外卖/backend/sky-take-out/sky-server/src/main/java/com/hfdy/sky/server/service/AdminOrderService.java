package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.OrderCancelDTO;
import com.hfdy.sky.pojo.dto.OrderConfirmDTO;
import com.hfdy.sky.pojo.dto.OrderPageQueryDTO;
import com.hfdy.sky.pojo.dto.OrderRejectionDTO;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.pojo.vo.OrderStatisticsVO;
import com.hfdy.sky.pojo.vo.OrderVO;

/**
 * @author hf-dy
 * @date 2024/10/2 23:12
 */

public interface AdminOrderService extends IService<Orders> {


    /**
     * 条件查询订单
     *
     * @param orderPageQueryDTO
     * @return
     */
    PageResult<OrderVO> conditionSearch(OrderPageQueryDTO orderPageQueryDTO);

    /**
     * 根据id获取订单详情
     *
     * @param id
     * @return
     */
    OrderVO getDetailById(Long id);


    /**
     * 统计各个状态的订单数量
     *
     * @return
     */
    OrderStatisticsVO statistics();


    /**
     * 接单
     *
     * @param orderConfirmDTO
     */
    void confirmOrder(OrderConfirmDTO orderConfirmDTO);


    /**
     * 拒单
     *
     * @param orderRejectionDTO
     */
    void rejectOrder(OrderRejectionDTO orderRejectionDTO);

    /**
     * 派送订单
     *
     * @param id
     */
    void deliveryOrder(Long id);


    /**
     * 取消订单l
     *
     * @param orderCancelDTO
     */
    void cancelOrder(OrderCancelDTO orderCancelDTO);

    /**
     * 完成订单
     *
     * @param id
     */
    void completeOrder(Long id);
}
