package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.DishDTO;
import com.hfdy.sky.pojo.dto.DishPageQueryDTO;
import com.hfdy.sky.pojo.entity.Dish;
import com.hfdy.sky.pojo.vo.DishVO;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/29 17:17
 */

public interface DishService extends IService<Dish> {

    /**
     * 根据categoryId查询菜品列表
     *
     * @param categoryId
     * @return
     */
    List<Dish> listByCategoryId(Long categoryId);

    /**
     * 新增菜品
     *
     * @param dishDTO
     */
    void savedWithFlavor(DishDTO dishDTO);


    /**
     * 菜品分页查询
     *
     * @param dishPageQueryDTO
     * @return
     */
    PageResult<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);


    /**
     * 根据id删除菜品
     *
     * @param idList
     * @return
     */
    void deleteByIds(List<Long> idList);

    /**
     * 根据id获取菜品
     *
     * @param id
     * @return
     */
    DishVO getById(Long id);


    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return 影响函数
     */
    int updateDish(DishDTO dishDTO);


    /**
     * 修改菜品状态
     *
     * @param status
     * @return
     */
    int changeDishStatus(Integer status, Integer id);

    /**
     * 用户端根据分类id获取菜品列表
     *
     * @param categoryId
     * @return
     */
    List<DishVO> listDishWithFlavor(Long categoryId);
}
