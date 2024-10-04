package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.vo.BusinessDataVO;
import com.hfdy.sky.pojo.vo.DishOverViewVO;
import com.hfdy.sky.pojo.vo.OrderOverViewVO;
import com.hfdy.sky.pojo.vo.SetmealOverViewVO;
import com.hfdy.sky.server.service.WorkSpaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/10/3 17:49
 */
@RestController
@RequestMapping("/admin/workspace")
@Slf4j
@Api("工作台接口")
public class WorkSpaceController {
    @Resource
    private WorkSpaceService workSpaceService;

    @GetMapping("/businessData")
    @ApiOperation("今日数据")
    public ApiResult<BusinessDataVO> getBusinessData() {
        log.info("获取今日数据");
        return ApiResult.success(workSpaceService.getBusinessData());
    }

    @GetMapping("/overviewOrders")
    @ApiOperation("订单总览")
    public ApiResult<OrderOverViewVO> getOrdersOverview() {
        log.info("获取订单总览");
        return ApiResult.success(workSpaceService.getOrdersOverview());
    }

    @GetMapping("/overviewDishes")
    @ApiOperation("菜品总览")
    public ApiResult<DishOverViewVO> getOverviewDishes() {
        log.info("菜品总览");
        return ApiResult.success(workSpaceService.getOverviewDishes());
    }

    @GetMapping("/overviewSetmeals")
    @ApiOperation("套餐总览")
    public ApiResult<SetmealOverViewVO> getOverviewSetmeals() {
        log.info("菜品总览");
        return ApiResult.success(workSpaceService.getOverviewSetmeals());
    }
}
