package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.entity.Setmeal;
import com.hfdy.sky.pojo.vo.DishItemVO;
import com.hfdy.sky.server.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 11:13
 */

@RestController("user-setmeal-controller")
@RequestMapping("/user/setmeal")
@Slf4j
@Api("用户端套餐相关")
public class SetmealController {
    @Resource
    private SetmealService setmealService;
    @Resource
    private RedisTemplate<String, List<Setmeal>> redisTemplate;

    @GetMapping("/list")
    @ApiOperation("根据分类id获取对应套餐")
    @Cacheable(cacheNames = "setmealCache",key="#categoryId")
    public ApiResult<List<Setmeal>> listSetmealByCategoryId(@RequestParam("categoryId") Long categoryId) {
        log.info("用户端获取分类对应的套餐：{}", categoryId);
        String key = "setmeal:" + categoryId;
        List<Setmeal> setmealList = redisTemplate.opsForValue().get(key);
        if (setmealList == null) {
            setmealList = setmealService.listSetmealByCategoryId(categoryId);
            redisTemplate.opsForValue().set(key, setmealList);
        }
        return ApiResult.success(setmealList);
    }

    @GetMapping("/dish/{id}")
    @ApiOperation("根据套餐id查询套餐包含的菜品")
    public ApiResult<List<DishItemVO>> getSetmealDishListBySetmealId(@PathVariable("id") Long setmealId) {
        log.info("用户端获取套餐对应的菜品：{}", setmealId);
        return ApiResult.success(setmealService.getSetmealDishListBySetmealId(setmealId));
    }
}
