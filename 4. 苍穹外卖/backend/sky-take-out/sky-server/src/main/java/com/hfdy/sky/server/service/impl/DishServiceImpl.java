package com.hfdy.sky.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.constant.StatusConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.CategoryNotFoundException;
import com.hfdy.sky.common.exception.DeletionNotAllowedException;
import com.hfdy.sky.common.exception.DishNameDuplicatedException;
import com.hfdy.sky.common.exception.DishNotExistsException;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.common.utils.AliOssUtil;
import com.hfdy.sky.pojo.dto.DishDTO;
import com.hfdy.sky.pojo.dto.DishPageQueryDTO;
import com.hfdy.sky.pojo.entity.*;
import com.hfdy.sky.pojo.vo.DishVO;
import com.hfdy.sky.server.mapper.*;
import com.hfdy.sky.server.service.DishService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author hf-dy
 * @date 2024/9/29 17:18
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private DishMapper dishMapper;
    @Resource
    private DishFlavorMapper dishFlavorMapper;
    @Resource
    private SetmealDishMapper setmealDishMapper;
    @Resource
    private AliOssUtil aliOssUtil;

    // 后面用到
    @Override
    public List<Dish> listByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, categoryId);
        return dishMapper.selectList(dishLambdaQueryWrapper);
    }


    @Override
    @Transactional
    public void savedWithFlavor(DishDTO dishDTO) {
        // 如果找不到所在的分类
        Category category = categoryMapper.selectById(dishDTO.getCategoryId());
        if (category == null) {
            // 后端再做一次校验
            throw new CategoryNotFoundException("请添加到已存在的分类");
        }
        // 如果菜品名已存在
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getName, dishDTO.getName());
        Dish existsDish = dishMapper.selectOne(dishLambdaQueryWrapper);
        if (existsDish != null) {
            throw new DishNameDuplicatedException("菜品名不能重复");
        }
        // 插入后才有id
        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .categoryId(dishDTO.getCategoryId())
                .price(dishDTO.getPrice())
                .image(dishDTO.getImage())
                .description(dishDTO.getDescription())
                .status(dishDTO.getStatus())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateUser(BaseContext.getCurrentId())
                .build();
        dishMapper.insert(dish);

        Long dishId = dish.getId();
        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if (flavorList != null && !flavorList.isEmpty()) {
            // 向口味表dish_flavor中插入
            flavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorMapper.insert(flavorList);
        }
    }

    /**
     * 根据菜品id查询菜品口味列表
     *
     * @param id
     * @return
     */
    private List<DishFlavor> getDishFlavorListByDishId(Long id) {
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
        return dishFlavorMapper.selectList(dishFlavorLambdaQueryWrapper);
    }


    @Override
    public PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        Page<Dish> dishPage = new Page<>(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(dishPageQueryDTO.getName() != null, Dish::getName, dishPageQueryDTO.getName())
                .eq(dishPageQueryDTO.getCategoryId() != null, Dish::getCategoryId, dishPageQueryDTO.getCategoryId())
                .eq(dishPageQueryDTO.getStatus() != null, Dish::getStatus, dishPageQueryDTO.getStatus());
        dishMapper.selectPage(dishPage, dishLambdaQueryWrapper);
        // 查找每个dish的dish_flavor、categoryName，删除createTime、createUser、updateUser
        List<DishVO> dishVOList = dishPage.getRecords().stream().map(i -> {
            List<DishFlavor> dishFlavorList = getDishFlavorListByDishId(i.getId());
            Category category = categoryMapper.selectById(i.getCategoryId());

            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(i, dishVO);
            dishVO.setCategoryName(category.getName());
            dishVO.setFlavors(dishFlavorList);
            return dishVO;
        }).toList();

        PageResult<DishVO> dishVOPageResult = new PageResult<>();
        dishVOPageResult.setTotal(dishPage.getTotal());
        dishVOPageResult.setRecords(dishVOList);

        return dishVOPageResult;
    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> idList) {
        idList.forEach(id -> {
            // 只要有一个不能删除，事务回滚
            Dish dish = dishMapper.selectById(id);
            // 如果在起售中，不能删除
            if (dish != null && Objects.equals(dish.getStatus(), StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
            // 如果有关联的套餐，不能删除
            LambdaQueryWrapper<SetmealDish> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
            setmealLambdaQueryWrapper.eq(SetmealDish::getDishId, id);
            List<SetmealDish> setmealDishList = setmealDishMapper.selectList(setmealLambdaQueryWrapper);
            if (setmealDishList != null && !setmealDishList.isEmpty()) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }

            // 可以删除
            dishMapper.deleteById(id);
            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
            dishFlavorMapper.delete(dishFlavorLambdaQueryWrapper);
            // 删除图片
            if (dish != null) {
                aliOssUtil.delete(dish.getImage());
            }
        });
    }

    @Override
    public DishVO getById(Long id) {
        Dish dish = dishMapper.selectById(id);
        if (dish == null) {
            return null;
        } else {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(dish, dishVO);

            // Category
            Category category = categoryMapper.selectById(dish.getCategoryId());
            dishVO.setCategoryName(category.getName());
            // dishFlavor list
            List<DishFlavor> dishFlavorList = getDishFlavorListByDishId(dish.getId());
            dishVO.setFlavors(dishFlavorList);
            return dishVO;
        }
    }

    @Override
    @Transactional
    public int updateDish(DishDTO dishDTO) {
        // 是否已存在
        Dish dish = dishMapper.selectById(dishDTO.getId());
        if (dish == null) {
            throw new DishNotExistsException("要修改的菜品不存在");
        }
        // 原图的objectName
        String objectName = dish.getImage();
        LambdaUpdateWrapper<Dish> dishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishLambdaUpdateWrapper.eq(Dish::getId, dishDTO.getId())
                .set(dishDTO.getName() != null, Dish::getName, dishDTO.getName())
                .set(dishDTO.getCategoryId() != null, Dish::getCategoryId, dishDTO.getCategoryId())
                .set(dishDTO.getPrice() != null, Dish::getPrice, dishDTO.getPrice())
                .set(dishDTO.getImage() != null, Dish::getImage, dishDTO.getImage())
                .set(dishDTO.getDescription() != null, Dish::getDescription, dishDTO.getDescription())
                .set(dishDTO.getStatus() != null, Dish::getStatus, dishDTO.getStatus())
                .set(Dish::getUpdateTime, LocalDateTime.now())
                .set(Dish::getUpdateUser, BaseContext.getCurrentId());
        int rows = dishMapper.update(dishLambdaUpdateWrapper);
        // 如果图片换了，删除oss的原图，新图在前端已经上传了，就不管了
        if (dishDTO.getImage() != null) {
            aliOssUtil.delete(objectName);
        }
        // 删除原有口味
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishDTO.getId());
        dishFlavorMapper.delete(dishFlavorLambdaQueryWrapper);
        // 插入最新口味
        dishFlavorMapper.insert(dishDTO.getFlavors().stream().peek(flavor -> flavor.setDishId(dishDTO.getId())).toList());
        return rows;
    }

    @Override
    public int changeDishStatus(Integer status, Integer id) {
        if (status != 0 && status != 1) {
            return 0;
        }
        LambdaUpdateWrapper<Dish> dishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishLambdaUpdateWrapper.eq(Dish::getId, id).set(Dish::getStatus, status);
        return dishMapper.update(dishLambdaUpdateWrapper);
    }

    @Override
    public List<DishVO> listDishWithFlavor(Long categoryId) {
        // 分类名
        Category category = categoryMapper.selectById(categoryId);
        if (category == null) return null;
        String categoryName = category.getName();
        // 查询分类id对应的所有的dish
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, categoryId).eq(Dish::getStatus, 1);
        List<Dish> dishes = dishMapper.selectList(dishLambdaQueryWrapper);
        // 构建DishVO list
        return dishes.stream().map(dish -> {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(dish, dishVO);
            dishVO.setCategoryName(categoryName);
            // 查dishFlavor list
            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
            List<DishFlavor> dishFlavorList = dishFlavorMapper.selectList(dishFlavorLambdaQueryWrapper);
            dishVO.setFlavors(dishFlavorList);
            return dishVO;
        }).toList();
    }
}
