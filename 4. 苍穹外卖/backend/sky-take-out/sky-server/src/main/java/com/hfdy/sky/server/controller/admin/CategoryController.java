package com.hfdy.sky.server.controller.admin;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.CategoryDTO;
import com.hfdy.sky.pojo.dto.CategoryPageQueryDTO;
import com.hfdy.sky.pojo.entity.Category;
import com.hfdy.sky.server.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/29 11:17
 */
@RestController("admin-category-controller")
@RequestMapping("/admin/category")
@Api("分类管理")
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("新增分类")
    public ApiResult<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return ApiResult.success();
    }

    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public ApiResult<PageResult<Category>> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询：{}", categoryPageQueryDTO);
        return ApiResult.success(categoryService.pageQuery(categoryPageQueryDTO));
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public ApiResult<String> deleteById(@ApiParam("分类id") @RequestParam("id") Long id) {
        log.info("删除分类：{}", id);
        categoryService.deleteById(id);
        return ApiResult.success();
    }

    @PutMapping
    @ApiOperation("修改分类")
    public ApiResult<Integer> update(@RequestBody CategoryDTO categoryDTO) {
        int rows = categoryService.update(categoryDTO);
        return ApiResult.success(rows);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public ApiResult<Integer> startOrStop(@ApiParam("状态") @PathVariable("status") Integer status, @ApiParam("分类id") @RequestParam("id") Long id) {
        int rows = categoryService.startOrStop(status, id);
        return ApiResult.success(rows);
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public ApiResult<List<Category>> listByType(@RequestParam("type") Integer type) {
        List<Category> categoryList = categoryService.listByType(type);
        return ApiResult.success(categoryList);
    }
}
