package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.constant.StatusConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.DeletionNotAllowedException;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.CategoryDTO;
import com.hfdy.sky.pojo.dto.CategoryPageQueryDTO;
import com.hfdy.sky.pojo.entity.Category;
import com.hfdy.sky.pojo.entity.Dish;
import com.hfdy.sky.pojo.entity.Setmeal;
import com.hfdy.sky.server.mapper.CategoryMapper;
import com.hfdy.sky.server.mapper.DishMapper;
import com.hfdy.sky.server.mapper.SetmealMapper;
import com.hfdy.sky.server.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/29 10:32
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private SetmealMapper setmealMapper;

    public void save(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .id(categoryDTO.getId())
                .type(categoryDTO.getType())
                .name(categoryDTO.getName())
                .sort(categoryDTO.getSort())
                .status(StatusConstant.ENABLE)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.insert(category);
    }

    public PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        long current = (categoryPageQueryDTO.getPage());
        long size = categoryPageQueryDTO.getPageSize();
        Page<Category> page = new Page<>(current, size);
        // 过滤分类名称和分类类型
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(categoryPageQueryDTO.getName() != null, Category::getName, categoryPageQueryDTO.getName())
                .eq(categoryPageQueryDTO.getType() != null, Category::getType, categoryPageQueryDTO.getType());
        // 分页
        categoryMapper.selectPage(page, lambdaQueryWrapper);
        PageResult<Category> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getRecords());
        return pageResult;
    }

    public void deleteById(Long id) {
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId, id);
        Long count = dishMapper.selectCount(lambdaQueryWrapper);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        // 如果当前分类关联了套餐，就不能删除
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        count = setmealMapper.selectCount(setmealLambdaQueryWrapper);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        // 可以删除
        categoryMapper.deleteById(id);
    }

    public int startOrStop(Integer status, Long id) {
        if (status != 0 && status != 1) {
            return 0;
        } else {
            LambdaUpdateWrapper<Category> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Category::getId, id).set(Category::getStatus, status);
            return categoryMapper.update(lambdaUpdateWrapper);
        }
    }

    public int update(CategoryDTO categoryDTO) {
        LambdaUpdateWrapper<Category> categoryLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        categoryLambdaUpdateWrapper.eq(Category::getId, categoryDTO.getId())
                .set(categoryDTO.getType() != null, Category::getType, categoryDTO.getType())
                .set(categoryDTO.getName() != null, Category::getName, categoryDTO.getName())
                .set(categoryDTO.getSort() != null, Category::getSort, categoryDTO.getSort());
        return categoryMapper.update(categoryLambdaUpdateWrapper);
    }

    public List<Category> listByType(Integer type) {
        LambdaUpdateWrapper<Category> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        // type是null则查询所有，不设置条件
        lambdaUpdateWrapper
                .eq(Category::getStatus, 1).eq(type != null, Category::getType, type);
        return categoryMapper.selectList(lambdaUpdateWrapper);
    }
}
