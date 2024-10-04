package com.hfdy.sky.server.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.exception.ShoppingCartBusinessException;
import com.hfdy.sky.pojo.dto.DishPageQueryDTO;
import com.hfdy.sky.pojo.dto.ShoppingCartDTO;
import com.hfdy.sky.pojo.entity.*;
import com.hfdy.sky.server.mapper.DishFlavorMapper;
import com.hfdy.sky.server.mapper.DishMapper;
import com.hfdy.sky.server.mapper.SetmealMapper;
import com.hfdy.sky.server.mapper.ShoppingCartMapper;
import com.hfdy.sky.server.service.ShoppingCartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 20:34
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private SetmealMapper setmealMapper;
    @Resource
    private DishFlavorMapper dishFlavorMapper;
    @Resource
    private DishMapper dishMapper;


    @Override
    public void add(ShoppingCartDTO shoppingCartDTO) {
        // 查询该用户是否有购物车
        Long userId = BaseContext.getCurrentId();
        // 是否已有该套餐的购物车
        if (shoppingCartDTO.getSetmealId() != null) {
            LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId).eq(ShoppingCart::getSetmealId, shoppingCartDTO.getSetmealId());
            ShoppingCart shoppingCart = shoppingCartMapper.selectOne(shoppingCartLambdaQueryWrapper);
            // 找到该套餐
            Setmeal setmeal = setmealMapper.selectById(shoppingCartDTO.getSetmealId());
            if (setmeal == null) throw new ShoppingCartBusinessException("找不到该套餐，确保id正确");
            // 存在，更新数量
            if (shoppingCart != null) {
                LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                shoppingCartLambdaUpdateWrapper.eq(ShoppingCart::getSetmealId, shoppingCartDTO.getSetmealId())
                        .setIncrBy(ShoppingCart::getNumber, 1);
                shoppingCartMapper.update(shoppingCartLambdaUpdateWrapper);
            } else {
                // 不存在，新建
                ShoppingCart shoppingCart1 = ShoppingCart.builder()
                        .userId(userId)
                        .name(setmeal.getName())
                        .setmealId(shoppingCartDTO.getSetmealId())
                        .number(1)
                        .amount(setmeal.getPrice())
                        .image(setmeal.getImage())
                        .createTime(LocalDateTime.now())
                        .build();
                shoppingCartMapper.insert(shoppingCart1);
            }
        }
        // 如果菜品和口味不为空
        if (shoppingCartDTO.getDishId() != null && shoppingCartDTO.getDishFlavor() != null) {
            // 菜品和口味是否对应
            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, shoppingCartDTO.getDishId());
            List<DishFlavor> dishFlavorList = dishFlavorMapper.selectList(dishFlavorLambdaQueryWrapper);
            List<String> existsFlavorList = new ArrayList<>();
            dishFlavorList.forEach(i -> {
                String flavorList = i.getValue().replace("[", "").replace("]", "").replace("\"", "");
                existsFlavorList.addAll(Arrays.stream(flavorList.split(",")).toList());
            });
            Arrays.stream(shoppingCartDTO.getDishFlavor().split(",")).forEach(i -> {
                if (!existsFlavorList.contains(i)) {
                    throw new ShoppingCartBusinessException("口味选择不正确，请重新选择");
                }
            });
            // 查询是否有已有菜品购物车
            LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
            shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId)
                    .eq(ShoppingCart::getDishId, shoppingCartDTO.getDishId())
                    .eq(ShoppingCart::getDishFlavor, shoppingCartDTO.getDishFlavor());
            ShoppingCart shoppingCart = shoppingCartMapper.selectOne(shoppingCartLambdaQueryWrapper);
            // 找到该菜品的详情
            Dish dish = dishMapper.selectById(shoppingCartDTO.getDishId());
            if (dish == null) throw new ShoppingCartBusinessException("找不到该菜品，确保id正确");
            // 存在，数量+1
            if (shoppingCart != null) {
                LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                shoppingCartLambdaUpdateWrapper.eq(ShoppingCart::getDishId, shoppingCartDTO.getDishId())
                        .setIncrBy(ShoppingCart::getNumber, 1); // 这里价格是单价
                shoppingCartMapper.update(shoppingCartLambdaUpdateWrapper);
            } else {
                // 不存在，新增
                ShoppingCart shoppingCart1 = ShoppingCart.builder()
                        .userId(userId)
                        .name(dish.getName())
                        .dishId(shoppingCartDTO.getDishId())
                        .dishFlavor(shoppingCartDTO.getDishFlavor())
                        .number(1)
                        .amount(dish.getPrice())
                        .image(dish.getImage())
                        .createTime(LocalDateTime.now())
                        .build();
                shoppingCartMapper.insert(shoppingCart1);
            }
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        return shoppingCartMapper.selectList(shoppingCartLambdaQueryWrapper);
    }

    @Override
    public int clean() {
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        return shoppingCartMapper.delete(shoppingCartLambdaQueryWrapper);
    }

    /**
     * 改变购物车中菜品/套餐的数量
     *
     * @param shoppingCartId
     * @param num            变化量
     * @return
     */
    private int changeNum(Long shoppingCartId, int num) {
        LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        shoppingCartLambdaUpdateWrapper.eq(ShoppingCart::getId, shoppingCartId)
                .setIncrBy(ShoppingCart::getNumber, num);
        int rows = shoppingCartMapper.update(shoppingCartLambdaUpdateWrapper);
        if (rows == 0) {
            throw new ShoppingCartBusinessException("购物车不存在");
        }
        return rows;
    }

    @Override
    public int add(Long shoppingCartId) {
        return changeNum(shoppingCartId, 1);
    }

    @Override
    public int sub(Long shoppingCartId) {
        return changeNum(shoppingCartId, -1);
    }
}
