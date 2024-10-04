package com.hfdy.sky.server.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hfdy.sky.pojo.entity.Orders;
import com.hfdy.sky.server.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/3 11:34
 */

@Component
@Slf4j
public class OrderTask {
    @Resource
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * *") // 每分钟执行一次，处理超时订单，改为已取消
    public void processTimeoutOrder() {
        log.info("处理超时订单：{}", LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(15);
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.PENDING_PAYMENT).le(Orders::getOrderTime, localDateTime);
        List<Orders> ordersList = orderMapper.selectList(ordersLambdaQueryWrapper);
        if (ordersList != null) {
            ordersList.forEach(i -> {
                i.setStatus(Orders.CANCELLED);
                i.setCancelTime(LocalDateTime.now());
                i.setCancelReason("支付超时，自动取消");
                orderMapper.updateById(i);
            });
        }
    }

    @Scheduled(cron = "0 0 1 * * *") // 每天1：00执行，修改昨天派送中订单为已完成
    public void processDeliverOrder() {
        log.info("粗粒派送中订单：{}", LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(1);
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getStatus, Orders.DELIVERY_IN_PROGRESS).le(Orders::getOrderTime, localDateTime);
        List<Orders> ordersList = orderMapper.selectList(ordersLambdaQueryWrapper);
        if (ordersList != null) {
            ordersList.forEach(i -> {
                i.setStatus(Orders.COMPLETED);
                orderMapper.updateById(i);
            });
        }
    }
}
