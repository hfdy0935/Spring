package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.*;
import com.hfdy.sky.pojo.vo.OrderPaymentVO;
import com.hfdy.sky.pojo.vo.OrderSubmitVO;
import com.hfdy.sky.pojo.vo.OrderVO;
import com.hfdy.sky.server.service.ClientOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author hf-dy
 * @date 2024/10/2 14:46
 */
@RestController("client-order-controller")
@RequestMapping("/user/order")
@Slf4j
@Api("用户端订单相关接口")
public class OrderController {

    @Resource
    private ClientOrderService clientOrderService;

    @PostMapping("/submit")
    @ApiOperation("提交订单")
    public ApiResult<OrderSubmitVO> submitOrder(@RequestBody OrderSubmitDTO orderSubmitDTO) {
        log.info("提交订单：{}", orderSubmitDTO);
        return ApiResult.success(clientOrderService.submit(orderSubmitDTO));
    }

    @PutMapping("/payment")
    @ApiOperation("支付订单")
    public ApiResult<OrderPaymentVO> pay(@RequestBody OrderPaymentDTO orderPaymentDTO) {
        log.info("支付订单：{}", orderPaymentDTO);
        return ApiResult.success(clientOrderService.pay(orderPaymentDTO));
    }

    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查看订单详情")
    public ApiResult<OrderVO> getOrderDetailById(@PathVariable("id") Long id) {
        log.info("查看订单详情：{}", id);
        return ApiResult.success(clientOrderService.getOrderDetailById(id));
    }

    @GetMapping("/historyOrders")
    @ApiOperation("获取历史订单")
    public ApiResult<PageResult<OrderVO>> getHistoryOrders(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("获取历史订单：{}", orderPageQueryDTO);
        return ApiResult.success(clientOrderService.pageQueryHistoryOrder(orderPageQueryDTO));
    }

    @GetMapping("/queryOrdersCheckStatus")
    @ApiOperation("查询已退款订单")
    public ApiResult<PageResult<OrderVO>> getRemainOrderList(OrderPageQueryDTO orderPageQueryDTO, Integer payStatus) {
        log.info("获取退款订单：{}", orderPageQueryDTO);
        return ApiResult.success(clientOrderService.pageQueryRefundOrder(orderPageQueryDTO, payStatus));
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public ApiResult<Void> cancelOrderById(@PathVariable("id") Long id, OrderCancelDTO orderCancelDTO) {
        log.info("取消订单：{}", id);
        orderCancelDTO.setId(id);
        clientOrderService.cancelOrder(orderCancelDTO);
        return ApiResult.success();
    }

    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public ApiResult<Void> repetitionOrder(@PathVariable("id") Long id) {
        log.info("再来一单：{}", id);
        clientOrderService.repetition(id);
        return ApiResult.success();
    }

    @PostMapping("/again")
    @ApiOperation("再来一单，没错另一个位置的...，请求体格式有点问题")
    public ApiResult<Void> again(Long id) {
        log.info("再来一单：{}", id);
        clientOrderService.repetition(id);
        return ApiResult.success();
    }

    @GetMapping("/reminder/{id}")
    @ApiOperation("用户催单")
    public ApiResult<Void> reminder(@PathVariable("id") Long id) {
        log.info("用户催单：{}", id);
        clientOrderService.reminder(id);
        return ApiResult.success();
    }
}
