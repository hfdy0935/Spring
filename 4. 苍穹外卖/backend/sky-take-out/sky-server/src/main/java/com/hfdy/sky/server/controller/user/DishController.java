package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.vo.DishVO;
import com.hfdy.sky.server.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 10:40
 */

@RestController("user-dish-controller")
@RequestMapping("/user/dish")
@Slf4j
@Api("用户端菜品管理")
public class DishController {
    @Resource
    private DishService dishService;

    @Resource
    private RedisTemplate<String, List<DishVO>> redisTemplate;

    @GetMapping("/list")
    @ApiOperation("根据分类id获取菜品")
    public ApiResult<List<DishVO>> listByCategoryId(@RequestParam("categoryId") Long categoryId) {
        log.info("根据分类id获取菜品列表：{}", categoryId);
        String key = "dish_vo_category:" + categoryId;
        List<DishVO> dishVOList = redisTemplate.opsForValue().get(key);
        if (dishVOList == null || dishVOList.isEmpty()) {
            dishVOList = dishService.listDishWithFlavor(categoryId);
            redisTemplate.opsForValue().set(key, dishVOList);
        }
        return ApiResult.success(dishVOList);
    }
}
