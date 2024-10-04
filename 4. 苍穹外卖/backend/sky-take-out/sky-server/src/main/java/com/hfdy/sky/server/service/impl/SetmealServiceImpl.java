package com.hfdy.sky.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.StatusConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.*;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.common.utils.AliOssUtil;
import com.hfdy.sky.pojo.dto.SetmealDTO;
import com.hfdy.sky.pojo.dto.SetmealPageQueryDTO;
import com.hfdy.sky.pojo.entity.*;
import com.hfdy.sky.pojo.vo.DishItemVO;
import com.hfdy.sky.pojo.vo.SetmealVO;
import com.hfdy.sky.server.mapper.CategoryMapper;
import com.hfdy.sky.server.mapper.DishMapper;
import com.hfdy.sky.server.mapper.SetmealDishMapper;
import com.hfdy.sky.server.mapper.SetmealMapper;
import com.hfdy.sky.server.service.SetmealService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author hf-dy
 * @date 2024/9/30 15:39
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SetmealDishMapper setmealDishMapper;
    @Resource
    private AliOssUtil aliOssUtil;
    @Resource
    private DishMapper dishMapper;


    @Override
    public PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        Page<Setmeal> setmealPage = new Page<>(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(setmealPageQueryDTO.getName() != null, Setmeal::getName, setmealPageQueryDTO.getName())
                .eq(setmealPageQueryDTO.getCategoryId() != null, Setmeal::getCategoryId, setmealPageQueryDTO.getCategoryId())
                .eq(setmealPageQueryDTO.getStatus() != null, Setmeal::getStatus, setmealPageQueryDTO.getStatus());
        setmealMapper.selectPage(setmealPage, setmealLambdaQueryWrapper);
        // 构建分页records
        List<SetmealVO> setmealVOList = setmealPage.getRecords().stream().map(setmeal -> {
            SetmealVO setmealVO = new SetmealVO();
            BeanUtils.copyProperties(setmeal, setmealVO);
            // 添加分类名
            Category category = categoryMapper.selectById(setmeal.getCategoryId());
            setmealVO.setCategoryName(category.getName());
            // 添加套餐和菜品的关联关系
            LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
            setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId());
            List<SetmealDish> setmealDishList = setmealDishMapper.selectList(setmealDishLambdaQueryWrapper);
            setmealVO.setSetmealDishes(setmealDishList);
            return setmealVO;
        }).toList();
        // 构建分页结果
        PageResult<SetmealVO> setmealVOPageResult = new PageResult<>();
        setmealVOPageResult.setTotal(setmealPage.getTotal());
        setmealVOPageResult.setRecords(setmealVOList);
        return setmealVOPageResult;
    }

    /**
     * 检查套餐是否符合规定
     *
     * @param setmealDTO
     */
    private void checkOneSetmeal(SetmealDTO setmealDTO) {
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        if (setmealDishList == null || setmealDishList.isEmpty()) {
            throw new SetmealSaveFailException("确保该套餐至少有一个菜品");
        }
        // 查找各个菜品，看是否存在，以及数量价格是否正确
        for (SetmealDish setmealDish : setmealDishList) {
            if (setmealDish.getCopies() <= 0) {
                throw new SetmealSaveFailException("确保已添加的菜品数量 > 0");
            }
            if (setmealDish.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new SetmealSaveFailException("确保价格 > 0");
            }
        }
    }

    @Override
    @Transactional
    public int save(SetmealDTO setmealDTO) {
        // 先查分类
        Category category = categoryMapper.selectById(setmealDTO.getCategoryId());
        if (category == null) {
            throw new CategoryNotFoundException("请选择已存在的分类");
        }
        // 检查
        checkOneSetmeal(setmealDTO);
        // 先添加setmeal
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        // 添加更新时间、分类名
        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setCreateUser(BaseContext.getCurrentId());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        int rows = setmealMapper.insert(setmeal);
        // 添加套餐中每个菜品，设置其中的套餐id为当前套餐
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes().stream().peek(setmealDish -> {
            setmealDish.setSetmealId(setmeal.getId());
            // 每样菜品最多99个
            int num = setmealDish.getCopies();
            setmealDish.setCopies(Math.min(num, 99));
        }).toList();
        setmealDishMapper.insert(setmealDishList);
        return rows;
    }


    @Override
    @Transactional
    public int deleteById(List<Long> ids) {
        // 如果其中有套餐不存在
        List<Setmeal> setmealList = setmealMapper.selectBatchIds(ids);
        if (setmealList == null || setmealList.size() < ids.size()) {
            throw new SetmealNotFoundException("请检查要删除的套餐中有不存在的套餐id");
        }
        // 图片列表
        List<String> imageUrlList = new ArrayList<>();
        // 如果其中有启用的套餐，则删除失败
        for (Setmeal setmeal : setmealList) {
            imageUrlList.add(setmeal.getImage());
            if (Objects.equals(setmeal.getStatus(), StatusConstant.ENABLE)) {
                throw new SetmealEnableFailedException("请检查要删除的套餐中有正在使用的套餐，需停用之后再删除l");
            }
        }
        // 删除该套餐列表包含的套餐-菜品记录
        LambdaUpdateWrapper<SetmealDish> setmealDishLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        setmealDishLambdaUpdateWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishMapper.delete(setmealDishLambdaUpdateWrapper);
        // 删除该套餐列表的图片
        imageUrlList.forEach(url -> {
            aliOssUtil.delete(url);
        });
        return setmealMapper.deleteByIds(ids);
    }

    @Override
    public int changeStatus(Integer status, Long id) {
        if (!StatusConstant.containes(status)) {
            throw new StatusNotFoundException("状态错误，只能设置为开启（1）或停用（0）");
        }
        LambdaUpdateWrapper<Setmeal> setmealLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        setmealLambdaUpdateWrapper.eq(Setmeal::getId, id).set(Setmeal::getStatus, status);
        int rows = setmealMapper.update(setmealLambdaUpdateWrapper);
        if (rows == 0) {
            throw new DeletionFailException("删除失败，请检查该套餐是否存在");
        }
        return rows;
    }

    @Override
    @Transactional
    public int update(SetmealDTO setmealDTO) {
        // 确保套餐存在
        Setmeal setmeal = setmealMapper.selectById(setmealDTO.getId());
        if (setmeal == null) {
            throw new SetmealNotFoundException("套餐不存在");
        }
        // 检查状态
        if (!StatusConstant.containes(setmeal.getStatus())) {
            throw new StatusNotFoundException("状态错误，只能设置为开启（1）或停用（0）");
        }
        // 检查
        checkOneSetmeal(setmealDTO);
        // 删除之前的图片
        aliOssUtil.delete(setmeal.getImage());
        // 更新
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setUpdateUser(BaseContext.getCurrentId());
        // 删除该套餐对应的套餐-菜品
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmealDTO.getId());
        setmealDishMapper.delete(setmealDishLambdaQueryWrapper);
        // 插入新的，前端传来的没用setmealId，需要再加一下
        List<SetmealDish> setmealDishList = (setmealDTO.getSetmealDishes().stream().peek(setmealDish -> {
            setmealDish.setSetmealId(setmealDTO.getId());
            // 每样菜品最多99个
            int num = setmealDish.getCopies();
            setmealDish.setCopies(Math.min(num, 99));
        }).toList());
        setmealDishMapper.insert(setmealDishList);
        // 套餐名不能重复，这里update会报错...还是先删后加吧
        setmealMapper.deleteById(setmeal.getId());
        return setmealMapper.insert(setmeal);
    }

    @Override
    public SetmealVO getById(Long id) {
        Setmeal setmeal = setmealMapper.selectById(id);
        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        // 分类名称
        Category category = categoryMapper.selectById(setmeal.getCategoryId());
        setmealVO.setCategoryName(category.getName());
        // 套餐和菜品的关联关系
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, id);
        List<SetmealDish> setmealDishList = setmealDishMapper.selectList(setmealDishLambdaQueryWrapper);
        setmealVO.setSetmealDishes(setmealDishList);

        return setmealVO;
    }

    @Override
    public List<Setmeal> listSetmealByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, 1).eq(Setmeal::getCategoryId, categoryId);
        return setmealMapper.selectList(setmealLambdaQueryWrapper);
    }

    @Override
    public List<DishItemVO> getSetmealDishListBySetmealId(Long setmealId) {
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmealId);
        List<SetmealDish> setmealDishList = setmealDishMapper.selectList(setmealDishLambdaQueryWrapper);
        // 构建结果
        return setmealDishList.stream().map(setmealDish -> {
            DishItemVO dishItemVO = new DishItemVO();
            BeanUtils.copyProperties(setmealDish, dishItemVO);
            // 缺少图片和描述，查dish表，加上
            Dish dish = dishMapper.selectById(setmealDish.getDishId());
            dishItemVO.setImage(dish.getImage());
            dishItemVO.setDescription(dish.getDescription());
            return dishItemVO;
        }).toList();
    }
}

