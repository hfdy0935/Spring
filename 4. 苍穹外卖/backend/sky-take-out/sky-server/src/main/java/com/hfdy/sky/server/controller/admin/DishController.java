package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.DishDTO;
import com.hfdy.sky.pojo.dto.DishPageQueryDTO;
import com.hfdy.sky.pojo.entity.Dish;
import com.hfdy.sky.pojo.vo.DishVO;
import com.hfdy.sky.server.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author hf-dy
 * @date 2024/9/29 17:16
 */
@RestController("admin-dish-controller")
@RequestMapping("/admin/dish")
@Slf4j
@Api("菜品管理")
public class DishController {
    @Resource
    private DishService dishService;
    @Resource
    private RedisTemplate<String, List<DishVO>> redisTemplate;

    private void clearCategoryCache(String pattern) {
        Set<String> keys = redisTemplate.keys("dish_vo_category:" + pattern);
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }

    private void clearCategoryCache(Long pattern) {
        clearCategoryCache(pattern + "");
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id获取菜品列表，数据库原始数据列表")
    public ApiResult<List<Dish>> listDishByCategoryId(@RequestParam("categoryId") Long categoryId) {
        log.info("根据分类id获取菜品列表：{}", categoryId);
        return ApiResult.success(dishService.listByCategoryId(categoryId));
    }


    @PostMapping
    @ApiOperation("新增菜品")
    public ApiResult<String> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.savedWithFlavor(dishDTO);
        // 清除缓存
        clearCategoryCache(dishDTO.getCategoryId());
        return ApiResult.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public ApiResult<PageResult<DishVO>> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("查询菜品：{}", dishPageQueryDTO);
        return ApiResult.success(dishService.pageQuery(dishPageQueryDTO));
    }

    @DeleteMapping
    @ApiOperation("删除菜品")
    public ApiResult<String> deleteById(@RequestParam("ids") List<Long> ids) {
        log.info("删除菜品：{}", ids);
        dishService.deleteByIds(ids);
        ids.forEach(this::clearCategoryCache);
        return ApiResult.success();
    }

    @GetMapping("{id}")
    @ApiOperation("根据id获取菜品详情")
    public ApiResult<DishVO> getById(@PathVariable("id") Long id) {
        log.info("根据id获取菜品：{}", id);
        return ApiResult.success(dishService.getById(id));
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public ApiResult<Integer> updateDish(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        clearCategoryCache(dishDTO.getCategoryId());
        return ApiResult.success(dishService.updateDish(dishDTO));
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品状态")
    public ApiResult<Integer> changeDishStatus(@PathVariable("status") Integer status, @RequestParam("id") Integer id) {
        log.info("修改菜品状态：{},{}", status, id);
        clearCategoryCache("*");
        return ApiResult.success(dishService.changeDishStatus(status, id));
    }
}
