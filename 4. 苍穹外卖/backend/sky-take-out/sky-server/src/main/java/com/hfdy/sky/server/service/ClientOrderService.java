package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.OrderCancelDTO;
import com.hfdy.sky.pojo.dto.OrderPageQueryDTO;
import com.hfdy.sky.pojo.dto.OrderPaymentDTO;
import com.hfdy.sky.pojo.dto.OrderSubmitDTO;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.pojo.vo.OrderPaymentVO;
import com.hfdy.sky.pojo.vo.OrderSubmitVO;
import com.hfdy.sky.pojo.vo.OrderVO;

/**
 * @author hf-dy
 * @date 2024/10/2 12:02
 */

public interface ClientOrderService extends IService<Orders> {


    /**
     * 提交订单
     *
     * @param orderSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO);


    /**
     * 支付订单
     *
     * @param orderPaymentDTO
     * @return
     */
    OrderPaymentVO pay(OrderPaymentDTO orderPaymentDTO);

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    OrderVO getOrderDetailById(Long id);

    /**
     * 分页查询历史订单
     *
     * @return
     */
    PageResult<OrderVO> pageQueryHistoryOrder(OrderPageQueryDTO orderPageQueryDTO);

    /**
     * 分页查询退款订单
     *
     * @param orderPageQueryDTO
     * @param pageStatus
     * @return
     */
    PageResult<OrderVO> pageQueryRefundOrder(OrderPageQueryDTO orderPageQueryDTO, Integer pageStatus);


    /**
     * 取消订单
     *
     * @param orderCancelDTO
     */
    void cancelOrder(OrderCancelDTO orderCancelDTO);

    /**
     * 再来一单
     * @param id
     */
    void repetition(Long id);


    /**
     * 用户催单
     * @param id
     */
    void reminder(Long id);
}
