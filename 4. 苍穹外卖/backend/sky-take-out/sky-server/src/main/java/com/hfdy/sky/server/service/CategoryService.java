package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.common.result.PageResult;
import com.hfdy.sky.pojo.dto.CategoryDTO;
import com.hfdy.sky.pojo.dto.CategoryPageQueryDTO;
import com.hfdy.sky.pojo.entity.Category;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/9/29 10:28
 */
public interface CategoryService extends IService<Category> {
    /**
     * 新增分类
     *
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);


    /**
     * 根据id删除分类
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     * 更新
     *
     * @param categoryDTO
     */
    int update(CategoryDTO categoryDTO);

    /**
     * 启动、禁用分类
     *
     * @param status
     * @param id
     */
    int startOrStop(Integer status, Long id);

    /**
     * 根据类型查询
     *
     * @param type
     * @return
     */
    List<Category> listByType(Integer type);
}
