package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/30 21:51
 */

@RestController("user-shop-controller")
@RequestMapping("/user/shop")
@Slf4j
@Api("用户端店铺相关接口")
public class ShopController {
    private static final String KEY = "SHOP_STATUS";

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public ApiResult<Integer> getStatus() {
        Integer status = redisTemplate.opsForValue().get(KEY);
        if (status == null) {
            status = 1;
        }
        log.info("查询店铺运营状态为：{}", status == 1 ? "营业中" : "打样中");
        return ApiResult.success(status);
    }
}
