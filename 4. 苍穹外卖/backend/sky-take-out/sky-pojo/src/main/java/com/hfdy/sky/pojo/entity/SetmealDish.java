package com.hfdy.sky.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 套餐菜品关系
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    //套餐id
    private Long setmealId;

    //菜品id
    @NotNull
    private Long dishId;

    //菜品名称 （冗余字段）
    @NotNull
    private String name;

    //菜品原价
    @NotNull
    private BigDecimal price;

    //份数
    @NotNull
    private Integer copies;
}
