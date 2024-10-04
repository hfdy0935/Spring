package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.pojo.dto.ShoppingCartDTO;
import com.hfdy.sky.pojo.entity.ShoppingCart;
import com.hfdy.sky.server.mapper.ShoppingCartMapper;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/10/1 20:32
 */

public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     */
    void add(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看购物车
     *
     * @return
     */
    List<ShoppingCart> getShoppingCart();

    /**
     * 情况购物车
     */
    int clean();

    /**
     * 增加购物车
     *
     * @param shoppingCartId
     * @return
     */
    int add(Long shoppingCartId);

    /**
     * 减少购物车
     *
     * @param shoppingCartId
     * @return
     */
    int sub(Long shoppingCartId);
}
