package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.SetmealDTO;
import com.hfdy.sky.pojo.dto.SetmealPageQueryDTO;
import com.hfdy.sky.pojo.vo.SetmealVO;
import com.hfdy.sky.server.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/30 15:45
 */

@RestController("admin-setmeal-controller")
@RequestMapping("/admin/setmeal")
@Slf4j
@Api("套餐管理")
public class SetmealController {
    @Resource
    private SetmealService setmealService;

    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public ApiResult<PageResult<SetmealVO>> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询：{}", setmealPageQueryDTO);
        return ApiResult.success(setmealService.pageQuery(setmealPageQueryDTO));
    }

    @PostMapping
    @ApiOperation("添加套餐")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public ApiResult<Integer> save(@RequestBody SetmealDTO setmealDTO) {
        log.info("添加套餐：{}", setmealDTO);
        return ApiResult.success(setmealService.save(setmealDTO));
    }

    @DeleteMapping
    @ApiOperation("根据id列表删除套餐")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public ApiResult<Integer> deleteById(@RequestParam("ids") List<Long> ids) {
        log.info("根据id删除套餐：{}", ids);
        return ApiResult.success(setmealService.deleteById(ids));
    }

    @PostMapping("status/{status}")
    @ApiOperation("修改套餐状态")
    @CacheEvict(cacheNames = "setmealCache", key = "#id")
    public ApiResult<Integer> changeSetmealStatus(@PathVariable("status") Integer status, @RequestParam("id") Long id) {
        log.info("修改套餐状态：status = {}, id = {}", status, id);
        return ApiResult.success(setmealService.changeStatus(status, id));
    }

    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")
    public ApiResult<Integer> update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐：{}", setmealDTO);
        return ApiResult.success(setmealService.update(setmealDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取套餐")
    public ApiResult<SetmealVO> getById(@PathVariable("id") Long id) {
        log.info("根据id获取套餐：{}", id);
        return ApiResult.success(setmealService.getById(id));
    }
}
