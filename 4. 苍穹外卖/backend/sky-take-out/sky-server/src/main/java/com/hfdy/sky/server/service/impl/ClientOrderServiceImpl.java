package com.hfdy.sky.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.AddressBookBusinessException;
import com.hfdy.sky.common.exception.OrderBusinessException;
import com.hfdy.sky.common.exception.ShoppingCartBusinessException;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.common.utils.ExceptionUtil;
import com.hfdy.sky.pojo.dto.OrderCancelDTO;
import com.hfdy.sky.pojo.dto.OrderPageQueryDTO;
import com.hfdy.sky.pojo.dto.OrderPaymentDTO;
import com.hfdy.sky.pojo.dto.OrderSubmitDTO;
import com.hfdy.sky.pojo.entity.*;
import com.hfdy.sky.pojo.vo.OrderPaymentVO;
import com.hfdy.sky.pojo.vo.OrderSubmitVO;
import com.hfdy.sky.pojo.vo.OrderVO;
import com.hfdy.sky.server.mapper.*;
import com.hfdy.sky.server.service.ClientOrderService;
import com.hfdy.sky.server.websocket.WebSocketServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author hf-dy
 * @date 2024/10/2 12:03
 */

@Service
@Slf4j
public class ClientOrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements ClientOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AddressBookMapper addressBookMapper;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private WebSocketServer webSocketServer;


    @Override
    @Transactional
    public OrderSubmitVO submit(OrderSubmitDTO orderSubmitDTO) {
        Long userId = BaseContext.getCurrentId();
        // 查询用户
        User user = userMapper.selectById(userId);
        // 如果收货地址为空
        AddressBook addressBook = addressBookMapper.selectById(orderSubmitDTO.getAddressBookId());
        ExceptionUtil.throwIf(addressBook == null, new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL));
        // 如果用户购物车为空
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper1.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectList(shoppingCartLambdaQueryWrapper1);
        ExceptionUtil.throwIf(shoppingCartList == null || shoppingCartList.isEmpty(), new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL));
        // 创建订单和订单详情
        BigDecimal amount = shoppingCartList.stream().map(ShoppingCart::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        // 转为Orders存入数据库
        Orders newOrders = Orders.builder()
                .number(String.valueOf(System.currentTimeMillis()))
                .userId(userId)
                .addressBookId(addressBook.getId())
                .orderTime(LocalDateTime.now())
                .status(Orders.PENDING_PAYMENT)
                .payStatus(Orders.UN_PAID)
                .phone(addressBook.getPhone())
                .address(addressBook.getDetail())
                .consignee(addressBook.getConsignee())
                .payMethod(orderSubmitDTO.getPayMethod())
                .remark(orderSubmitDTO.getRemark())
                .estimatedDeliveryTime(orderSubmitDTO.getEstimatedDeliveryTime())
                .deliveryStatus(orderSubmitDTO.getDeliveryStatus())
                .tablewareNumber(orderSubmitDTO.getTablewareNumber())
                .tablewareStatus(orderSubmitDTO.getTablewareStatus())
                .packAmount(orderSubmitDTO.getPackAmount())
                .amount(amount)
                .userName(user.getName())
                .build();
        orderMapper.insert(newOrders);
        // 插入订单明细数据
        List<OrderDetail> orderDetails = shoppingCartList.stream().map(shoppingCart -> {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart, orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(newOrders.getId());
            return orderDetail;
        }).toList();
        orderDetailMapper.insert(orderDetails);
        // 购物车转为订单，删除该用户的购物车
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        shoppingCartMapper.delete(shoppingCartLambdaQueryWrapper);

        // 封装OrderSubmitVo
        return OrderSubmitVO.builder()
                .id(newOrders.getId())
                .orderNumber(newOrders.getNumber())
                .orderAmount(newOrders.getAmount())
                .orderTime(newOrders.getOrderTime())
                .build();
    }

    @Override
    public OrderPaymentVO pay(OrderPaymentDTO orderPaymentDTO) {
        Long userId = BaseContext.getCurrentId();
        // 查看订单状态
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getNumber, orderPaymentDTO.getOrderNumber());
        Orders orders = orderMapper.selectOne(ordersLambdaQueryWrapper);
        // 判断订单存在及状态
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        ExceptionUtil.throwIf(!orders.getStatus().equals(Orders.PENDING_PAYMENT), new OrderBusinessException(MessageConstant.ORDER_STATUS_ERROR));
        // 修改订单状态
        LambdaUpdateWrapper<Orders> ordersLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        ordersLambdaUpdateWrapper
                .eq(Orders::getUserId, userId)
                .eq(Orders::getNumber, orderPaymentDTO.getOrderNumber())
                .set(Orders::getPayMethod, orderPaymentDTO.getPayMethod())
                .set(Orders::getStatus, Orders.REFUND)
                .set(Orders::getPayStatus, Orders.PAID)
                .set(Orders::getCheckoutTime, LocalDateTime.now());
        int rows = orderMapper.update(ordersLambdaUpdateWrapper);
        ExceptionUtil.throwIf(rows == 0, new OrderBusinessException("你暂无订单"));
        // 给管理端发送新订单
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
        map.put("orderId", orders.getId());
        map.put("content", "订单号：" + orders.getNumber());
        webSocketServer.sendToAllClient(JSON.toJSONString(map));
        //
        return OrderPaymentVO.builder()
                .nonceStr("random string")
                .paySign("123")
                .timeStamp(System.currentTimeMillis() + "")
                .signType("I don't know")
                .packageStr("some string called prepay_id")
                .build();
    }


    /**
     * 根据Orders获取OrderVO
     *
     * @param orders
     * @return
     */
    private OrderVO getOrderVoByOrders(Orders orders) {
        LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId, orders.getId());
        List<OrderDetail> orderDetailList = orderDetailMapper.selectList(orderDetailLambdaQueryWrapper);
        // 构造orderDishes
        String orderDishes = orderDetailList.stream().map(OrderDetail::getName).collect(Collectors.joining(","));
        //
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orders, orderVO);
        orderVO.setOrderDishes(orderDishes);
        orderVO.setOrderDetailList(orderDetailList);
        return orderVO;
    }

    @Override
    public OrderVO getOrderDetailById(Long orderId) {
        Long userId = BaseContext.getCurrentId();
        // 如果不是自己的订单，查询失败
        Orders orders = orderMapper.selectById(orderId);
        ExceptionUtil.throwIf(!userId.equals(orders.getUserId()), new OrderBusinessException("只能查自己的订单"));
        return getOrderVoByOrders(orders);
    }

    @Override
    public PageResult<OrderVO> pageQueryHistoryOrder(OrderPageQueryDTO orderPageQueryDTO) {
        Long userId = BaseContext.getCurrentId();
        Page<Orders> ordersPage = new Page<>(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 由于前端全部订单这里都可以催单，会出现未付款就催单，这里只返回已接单3、配送中4两种情况
        ordersLambdaQueryWrapper.eq(Orders::getUserId, userId)
                .eq(orderPageQueryDTO.getStatus() != null, Orders::getStatus, orderPageQueryDTO.getStatus());
//                .in(Orders::getStatus, Orders.CONFIRMED, Orders.DELIVERY_IN_PROGRESS);
        orderMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        return getOrderVOPageResult(ordersPage);
    }

    /**
     * 根据分页查询结果构建vo
     *
     * @param ordersPage
     * @return
     */
    private PageResult<OrderVO> getOrderVOPageResult(Page<Orders> ordersPage) {
        List<OrderVO> orderVOList = ordersPage.getRecords().stream().map(this::getOrderVoByOrders).toList();
        PageResult<OrderVO> orderVOPageResult = new PageResult<>();
        orderVOPageResult.setTotal(ordersPage.getTotal());
        orderVOPageResult.setRecords(orderVOList);
        return orderVOPageResult;
    }

    @Override
    public PageResult<OrderVO> pageQueryRefundOrder(OrderPageQueryDTO orderPageQueryDTO, Integer payStatus) {
        Long userId = BaseContext.getCurrentId();
        Page<Orders> ordersPage = new Page<>(orderPageQueryDTO.getPage(), orderPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(Orders::getUserId, userId)
                .eq(Orders::getPayStatus, payStatus);
        orderMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        return getOrderVOPageResult(ordersPage);
    }

    @Override
    public void cancelOrder(OrderCancelDTO orderCancelDTO) {
        Long userId = BaseContext.getCurrentId();
        // 根据id查询订单
        Orders orders = orderMapper.selectById(orderCancelDTO.getId());
        // 如果订单不存在
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        // 如果不是当前用户的
        ExceptionUtil.throwIf(!orders.getUserId().equals(userId), new OrderBusinessException("订单不是你的"));
        // 待支付和待接单状态，可以直接取消
        Integer status = orders.getStatus();
        if (status.equals(Orders.PENDING_PAYMENT) || status.equals(Orders.TO_BE_CONFIRMED)) {
            orders.setStatus(Orders.CANCELLED);
            orders.setCancelReason(orderCancelDTO.getCancelReason() == null ? "用户取消" : orderCancelDTO.getCancelReason());
            orders.setCancelTime(LocalDateTime.now());
            // 待接单时还需要退款
            if (status.equals(Orders.TO_BE_CONFIRMED)) {
                orders.setPayStatus(Orders.REFUND);
                // 退款
                log.info("退款");
            }
            orderMapper.updateById(orders);
        }
        // 商家已接单/派送中，需要和商家沟通
        ExceptionUtil.throwIf(status.equals(Orders.CONFIRMED) || status.equals(Orders.DELIVERY_IN_PROGRESS), new OrderBusinessException("商家已接单，请和商家沟通"));
    }

    @Override
    public void repetition(Long id) {
        Orders orders = orderMapper.selectById(id);
        ExceptionUtil.throwIf(orders == null, new OrderBusinessException(MessageConstant.ORDER_NOT_FOUND));
        Orders newOrders = new Orders();
        BeanUtils.copyProperties(orders, newOrders);
        newOrders.setNumber(String.valueOf(System.currentTimeMillis()));
        newOrders.setOrderTime(LocalDateTime.now());
        newOrders.setId(null);
        newOrders.setStatus(Orders.PENDING_PAYMENT);
        newOrders.setPayStatus(Orders.UN_PAID);
        orderMapper.insert(newOrders);
        // 插入订单对应的详情
        LambdaQueryWrapper<OrderDetail> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ordersLambdaQueryWrapper.eq(OrderDetail::getOrderId, id);
        List<OrderDetail> orderDetailList = orderDetailMapper.selectList(ordersLambdaQueryWrapper);
        List<OrderDetail> orderDetailList1 = orderDetailList.stream().peek(i -> {
            i.setOrderId(newOrders.getId());
            i.setId(null);
        }).toList();
        orderDetailMapper.insert(orderDetailList1);
    }

    @Override
    public void reminder(Long id) {
        Orders orders = orderMapper.selectById(id);
        if (orders != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", 2);
            map.put("orderId", id);
            map.put("content", "订单号：" + orders.getNumber());
            webSocketServer.sendToAllClient(JSON.toJSONString(map));
        }
    }
}
