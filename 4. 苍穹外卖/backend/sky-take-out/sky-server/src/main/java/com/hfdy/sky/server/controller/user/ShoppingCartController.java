package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.dto.ShoppingCartDTO;
import com.hfdy.sky.pojo.entity.ShoppingCart;
import com.hfdy.sky.server.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 21:42
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api("用户购物车相关接口")
public class ShoppingCartController {
    @Resource
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public ApiResult<Void> add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车：{}", shoppingCartDTO);
        shoppingCartService.add(shoppingCartDTO);
        return ApiResult.success();
    }

    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public ApiResult<List<ShoppingCart>> getShoppingCart() {
        log.info("获取购物车用户：{}", BaseContext.getCurrentId());
        return ApiResult.success(shoppingCartService.getShoppingCart());
    }

    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public ApiResult<Integer> clean() {
        log.info("清空购物车用户：{}", BaseContext.getCurrentId());
        return ApiResult.success(shoppingCartService.clean());
    }

    // 增加和减少的，前端有点问题，用api测试没问题
    @PostMapping("/add-shopping-cart")
    @ApiOperation("购物车中增加一份已有的")
    public ApiResult<Integer> add(@RequestParam("shoppingCartId") Long shoppingCartId) {
        log.info("增加购物车：{}", shoppingCartId);
        return ApiResult.success(shoppingCartService.add(shoppingCartId));
    }

    @PostMapping("/sub-shopping-cart")
    @ApiOperation("购物车中减少一份已有的")
    public ApiResult<Integer> sub(@RequestParam("shoppingCartId") Long shoppingCartId) {
        log.info("减少购物车：{}", shoppingCartId);
        return ApiResult.success(shoppingCartService.sub(shoppingCartId));
    }
}
