package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author hf-dy
 * @date 2024/9/30 21:41
 */
@RestController("admin-shop-controller")
@RequestMapping("/admin/shop")
@Api("店铺操作相关接口")
@Slf4j
public class ShopController {
    private static final String KEY = "SHOP_STATUS";

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public ApiResult<Void> setStatus(@PathVariable("status") Integer status) {
        log.info("设置营业状态为：{}", status == 1 ? "营业中" : "打样中");
        redisTemplate.opsForValue().set(KEY, status);
        return ApiResult.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public ApiResult<Integer> getStatus() {
        Integer status = redisTemplate.opsForValue().get(KEY);
        status = status == null ? 1 : status;
        log.info("查询店铺运营状态为：{}", status == 1 ? "营业中" : "打样中");
        return ApiResult.success(status);
    }
}
