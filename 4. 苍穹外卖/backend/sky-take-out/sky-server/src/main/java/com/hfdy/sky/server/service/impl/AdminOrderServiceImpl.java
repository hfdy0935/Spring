package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.exception.OrderBusinessException;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.common.utils.ExceptionUtil;
import com.hfdy.sky.pojo.dto.OrderCancelDTO;
import com.hfdy.sky.pojo.dto.OrderConfirmDTO;
import com.hfdy.sky.pojo.dto.OrderPageQueryDTO;
import com.hfdy.sky.pojo.dto.OrderRejectionDTO;
import com.hfdy.sky.pojo.entity.OrderDetail;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.pojo.vo.OrderStatisticsVO;
import com.hfdy.sky.pojo.vo.OrderVO;
import com.hfdy.sky.server.mapper.OrderDetailMapper;
import com.hfdy.sky.server.mapper.OrderMapper;
import com.hfdy.sky.server.service.AdminOrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hf-dy
 * @date 2024/10/2 23:13
 */

@Service
@Slf4j
public class AdminOrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements AdminOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;


    /**
     * 根据orders查询，组合得到orderVo
     *
     * @param orders
     * @return
     */
    private OrderVO getOrderVoByOrder(Orders orders) {
        LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId, orders.getId());
        // orderDetail
        List<OrderDetail> orderDetailList = orderDetailMapper.selectList(orderDetailLambdaQueryWrapper);
        // orderDishes
        String orderDishes = orderDetailList.stream().map(OrderDetail::getName).collect(Collectors.joining(","));
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDishes(orderDishes);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

    @Override
    public PageResult<OrderVO> conditionSearch(OrderPageQueryDTO orderPageQueryDTO) {
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.like(orderPageQueryDTO.getNumber() != null, Orders::getNumber, orderPageQueryDTO.getNumber())
                .like(orderPageQueryDTO.getPhone() != null, Orders::getPhone, orderPageQueryDTO.getPhone())
                .eq(orderPageQueryDTO.getStatus() != null, Orders::getStatus, orderPageQueryDTO.getStatus())
                .between(orderPageQueryDTO.getBeginTime() != null && orderPageQueryDTO.getEndTime() != null, Orders::getOrderTime, orderPageQueryDTO.getBeginTime(), orderPageQueryDTO.getEndTime())
                .eq(orderPageQueryDTO.getUserId() != null, Orders::getUserId, orderPageQueryDTO.getUserId());
        Page<Orders> ordersPage = new Page<>(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        orderMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        List<OrderVO> orderVOList = ordersPage.getRecords().stream().map(this::getOrderVoByOrder).toList();
        PageResult<OrderVO> orderVOPageResult = new PageResult<>();
        orderVOPageResult.setTotal(ordersPage.getTotal());
        orderVOPageResult.setRecords(orderVOList);
        return orderVOPageResult;
    }

    @Override
    public OrderVO getDetailById(Long id) {
        Orders orders = orderMapper.selectById(id);
        return getOrderVoByOrder(orders);
    }

    @Override
    public OrderStatisticsVO statistics() {
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.TO_BE_CONFIRMED);
        int toBeConfirmed = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        ordersLambdaQueryWrapper.clear();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.CONFIRMED);
        int confirmed = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        ordersLambdaQueryWrapper.clear();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.DELIVERY_IN_PROGRESS);
        int deliveryInProgress = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        return new OrderStatisticsVO(toBeConfirmed, confirmed, deliveryInProgress);
    }


    @Override
    public void confirmOrder(OrderConfirmDTO orderConfirmDTO) {
        // 前端发的时候只有订单id，所以这里不管status了
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper.eq(Orders::getId, orderConfirmDTO.getId())
                .set(Orders::getStatus, Orders.CONFIRMED);
        orderMapper.update(ordersLambdaUpdateWrapper);
    }

    @Override
    public void rejectOrder(OrderRejectionDTO orderRejectionDTO) {
        // 只有订单存在且为待接单2状态才能拒单
        Orders orders = orderMapper.selectById(orderRejectionDTO.getId());
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        ExceptionUtil.throwIf(!orders.getStatus().equals(Orders.TO_BE_CONFIRMED), new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR));
        // 可以拒单
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper.eq(Orders::getId, orderRejectionDTO.getId())
                .set(Orders::getStatus, Orders.CANCELLED)
                .set(Orders::getRejectionReason, orderRejectionDTO.getRejectionReason());
        // 退款
        if (orders.getPayStatus().equals(Orders.PAID))
            log.info("退款订单id：{}，原因：{}", orderRejectionDTO.getId(), orderRejectionDTO.getRejectionReason());
        orderMapper.update(ordersLambdaUpdateWrapper);
    }

    @Override
    public void deliveryOrder(Long id) {
        Orders orders = orderMapper.selectById(id);
        // 只有已接单可修改为派送中
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        ExceptionUtil.throwIf(!orders.getStatus().equals(Orders.CONFIRMED), new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR));
        // 可以修改
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper.eq(Orders::getId, id)
                .set(Orders::getStatus, Orders.DELIVERY_IN_PROGRESS)
                .set(Orders::getDeliveryTime, LocalDateTime.now());
        orderMapper.update(ordersLambdaUpdateWrapper);
    }

    @Override
    public void cancelOrder(OrderCancelDTO orderCancelDTO) {
        Orders orders = orderMapper.selectById(orderCancelDTO.getId());
        // 只能取消待派送和派送中的订单
        ExceptionUtil.throwIf(!Arrays.asList(Orders.DELIVERY_IN_PROGRESS, Orders.CONFIRMED).contains(orders.getStatus()), new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR));
        // 可以取消
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper.eq(Orders::getId, orderCancelDTO.getId())
                .set(Orders::getStatus, Orders.CANCELLED)
                .set(Orders::getCancelReason, orderCancelDTO.getCancelReason());
        // 退款
        if (orders.getPayStatus().equals(Orders.PAID))
            log.info("退款订单id：{}，原因：{}", orderCancelDTO.getId(), orderCancelDTO.getCancelReason());
        orderMapper.update(ordersLambdaUpdateWrapper);
    }

    @Override
    public void completeOrder(Long id) {
        Orders orders = orderMapper.selectById(id);
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        ExceptionUtil.throwIf(!orders.getStatus().equals(Orders.DELIVERY_IN_PROGRESS));
        // 可以完成
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper.eq(Orders::getId, id)
                .set(Orders::getStatus, Orders.COMPLETED);
        orderMapper.update(ordersLambdaUpdateWrapper);

    }
}
