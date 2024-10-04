package com.hfdy.sky.pojo.dto;


import com.hfdy.sky.pojo.entity.SetmealDish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@ApiModel("套餐")
public class SetmealDTO implements Serializable {

    @NotNull
    @ApiModelProperty("id")
    private Long id;

    @NotNull
    @ApiModelProperty("所在分类id")
    private Long categoryId;

    @NotNull
    @ApiModelProperty("套餐名称")
    private String name;

    @NotNull
    @ApiModelProperty("套餐价格")
    private BigDecimal price;

    @NotNull
    @ApiModelProperty("状态 0:停用 1:启用")
    private Integer status;


    @ApiModelProperty("描述信息")
    private String description;

    @NotNull
    @ApiModelProperty("图片")
    private String image;

    @NotNull
    @ApiModelProperty("套餐菜品关系")
    private List<SetmealDish> setmealDishes = new ArrayList<>();
}
