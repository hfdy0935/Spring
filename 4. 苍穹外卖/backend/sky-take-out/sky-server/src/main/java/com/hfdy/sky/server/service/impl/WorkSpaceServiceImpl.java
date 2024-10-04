package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hfdy.sky.pojo.entity.Dish;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.pojo.entity.Setmeal;
import com.hfdy.sky.pojo.entity.User;
import com.hfdy.sky.pojo.vo.BusinessDataVO;
import com.hfdy.sky.pojo.vo.DishOverViewVO;
import com.hfdy.sky.pojo.vo.OrderOverViewVO;
import com.hfdy.sky.pojo.vo.SetmealOverViewVO;
import com.hfdy.sky.server.mapper.DishMapper;
import com.hfdy.sky.server.mapper.OrderMapper;
import com.hfdy.sky.server.mapper.SetmealMapper;
import com.hfdy.sky.server.mapper.UserMapper;
import com.hfdy.sky.server.service.WorkSpaceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/3 17:50
 */

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;


    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        // 新用户数量
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.between(User::getCreateTime, begin, end);
        int newUserCount = userMapper.selectCount(userLambdaQueryWrapper).intValue();
        // 订单完成率
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.between(Orders::getOrderTime, begin, end);
        int totalOrderCount = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.COMPLETED);
        List<Orders> ordersList = orderMapper.selectList(ordersLambdaQueryWrapper); // 已完成的订单列表
        int completedOrderCount = ordersList.size();
        double orderCompletionRate = totalOrderCount == 0 ? 0d : (double) completedOrderCount / totalOrderCount;
        // 营业额
        double turnover = ordersList.stream().map(Orders::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        // 平均客单价
        double unitPrice = ordersList.isEmpty() ? 0 : turnover / ordersList.size();
        //
        return BusinessDataVO.builder()
                .newUsers(newUserCount)
                .orderCompletionRate(orderCompletionRate)
                .turnover(turnover)
                .unitPrice(unitPrice)
                .validOrderCount(completedOrderCount)
                .build();
    }


    @Override
    public BusinessDataVO getBusinessData() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return getBusinessData(yesterday, today);
    }

    @Override
    public OrderOverViewVO getOrdersOverview() {
        // 全部订单数量
        int totalOrderCount = orderMapper.selectCount(null).intValue();
        // 已取消订单数量
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.CANCELLED);
        int cancelOrderCount = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        // 已完成订单数量
        ordersLambdaQueryWrapper.clear();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.COMPLETED);
        int completedOrderCount = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        // 待派送订单数量
        ordersLambdaQueryWrapper.clear();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.CONFIRMED);
        int deliveredOrderCount = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        // 待接单订单数量
        ordersLambdaQueryWrapper.clear();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.TO_BE_CONFIRMED);
        int waitingOrderCount = orderMapper.selectCount(ordersLambdaQueryWrapper).intValue();
        //
        return OrderOverViewVO.builder()
                .allOrders(totalOrderCount)
                .cancelledOrders(cancelOrderCount)
                .completedOrders(completedOrderCount)
                .deliveredOrders(deliveredOrderCount)
                .waitingOrders(waitingOrderCount)
                .build();
    }

    @Override
    public DishOverViewVO getOverviewDishes() {
        int totalCount = dishMapper.selectCount(null).intValue();
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getStatus, 1);
        int soldDishCount = dishMapper.selectCount(dishLambdaQueryWrapper).intValue();
        return DishOverViewVO.builder()
                .sold(soldDishCount)
                .discontinued(totalCount - soldDishCount)
                .build();
    }

    @Override
    public SetmealOverViewVO getOverviewSetmeals() {
        int totalCount = setmealMapper.selectCount(null).intValue();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, 1);
        int soldSetmealCount = setmealMapper.selectCount(setmealLambdaQueryWrapper).intValue();
        return SetmealOverViewVO.builder()
                .sold(soldSetmealCount)
                .discontinued(totalCount - soldSetmealCount)
                .build();
    }
}
