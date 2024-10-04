package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.OrderCancelDTO;
import com.hfdy.sky.pojo.dto.OrderConfirmDTO;
import com.hfdy.sky.pojo.dto.OrderPageQueryDTO;
import com.hfdy.sky.pojo.dto.OrderRejectionDTO;
import com.hfdy.sky.pojo.vo.OrderStatisticsVO;
import com.hfdy.sky.pojo.vo.OrderVO;
import com.hfdy.sky.server.service.AdminOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author hf-dy
 * @date 2024/10/2 23:10
 */

@RestController("admin-order-controller")
@RequestMapping("/admin/order")
@Slf4j
@Api("管理端订单接口")
public class OrderController {
    @Resource
    private AdminOrderService adminOrderService;


    @GetMapping("/conditionSearch")
    @ApiOperation("搜索订单")
    public ApiResult<PageResult<OrderVO>> conditionSearch(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("条件查询：{}", orderPageQueryDTO);
        return ApiResult.success(adminOrderService.conditionSearch(orderPageQueryDTO));
    }

    @GetMapping("/details/{id}")
    @ApiOperation("根据id获取订单详情")
    public ApiResult<OrderVO> getDetailById(@PathVariable("id") Long id) {
        log.info("根据id获取订单详情：{}", id);
        return ApiResult.success(adminOrderService.getDetailById(id));
    }

    @GetMapping("/statistics")
    @ApiOperation("统计")
    public ApiResult<OrderStatisticsVO> statistics() {
        log.info("统计各个状态的订单数量");
        return ApiResult.success(adminOrderService.statistics());
    }

    @PutMapping("/confirm")
    @ApiOperation("接单")
    public ApiResult<Void> confirmOrder(@RequestBody OrderConfirmDTO orderConfirmDTO) {
        log.info("接单：{}", orderConfirmDTO);
        adminOrderService.confirmOrder(orderConfirmDTO);
        return ApiResult.success();
    }

    @PutMapping("/rejection")
    @ApiOperation("拒单")
    public ApiResult<Void> rejectOrder(@RequestBody OrderRejectionDTO orderRejectionDTO) {
        log.info("拒单：{}", orderRejectionDTO);
        adminOrderService.rejectOrder(orderRejectionDTO);
        return ApiResult.success();
    }

    @PutMapping("/delivery/{id}")
    @ApiOperation("派送")
    public ApiResult<Void> deliveryOrder(@PathVariable("id") Long id) {
        log.info("派送：{}", id);
        adminOrderService.deliveryOrder(id);
        return ApiResult.success();
    }

    @PutMapping("/cancel")
    @ApiOperation("取消订单")
    public ApiResult<Void> cancelOrder(@RequestBody OrderCancelDTO orderCancelDTO) {
        log.info("取消订单：{}", orderCancelDTO);
        adminOrderService.cancelOrder(orderCancelDTO);
        return ApiResult.success();
    }

    @PutMapping("/complete/{id}")
    @ApiOperation("完成订单")
    public ApiResult<Void> completeOrder(@PathVariable("id") Long id) {
        log.info("完成订单：{}", id);
        adminOrderService.completeOrder(id);
        return ApiResult.success();
    }
}
