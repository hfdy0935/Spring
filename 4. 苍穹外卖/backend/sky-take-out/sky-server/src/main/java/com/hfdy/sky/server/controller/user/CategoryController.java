package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.pojo.entity.Category;
import com.hfdy.sky.server.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 10:35
 */
@RestController("user-category-controller")
@RequestMapping("/user/category")
@Slf4j
@Api("用户获取菜品分类管理")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    @ApiOperation("根据分类id获取分类")
    public ApiResult<List<Category>> listByType(@RequestParam(value = "type", required = false) Integer type) {
        log.info("用户端根据分类id获取分类：{}", type);
        List<Category> categoryList = categoryService.listByType(type);
        return ApiResult.success(categoryList);
    }

}
