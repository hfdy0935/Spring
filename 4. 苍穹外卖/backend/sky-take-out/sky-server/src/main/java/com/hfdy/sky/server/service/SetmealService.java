package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.SetmealDTO;
import com.hfdy.sky.pojo.dto.SetmealPageQueryDTO;
import com.hfdy.sky.pojo.entity.Setmeal;
import com.hfdy.sky.pojo.vo.DishItemVO;
import com.hfdy.sky.pojo.vo.SetmealVO;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/30 15:40
 */

public interface SetmealService extends IService<Setmeal> {

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 新增套餐
     *
     * @param setmealDTO
     * @return
     */
    int save(SetmealDTO setmealDTO);

    /**
     * 根据套餐id列表删除套餐
     *
     * @param ids
     * @return
     */
    int deleteById(List<Long> ids);

    /**
     * 根据id修改套餐状态
     *
     * @param status
     * @param id
     * @return
     */
    int changeStatus(Integer status, Long id);

    /**
     * 更新
     *
     * @param setmealDTO
     * @return
     */
    int update(SetmealDTO setmealDTO);

    /**
     * 根据id获取套餐
     *
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 用户端根据分类id获取套餐列表
     *
     * @param categoryId
     * @return
     */
    List<Setmeal> listSetmealByCategoryId(Long categoryId);

    /**
     * 根据套餐id查询对应的菜品列表
     *
     * @param setmealId
     * @return
     */
    List<DishItemVO> getSetmealDishListBySetmealId(Long setmealId);


}
