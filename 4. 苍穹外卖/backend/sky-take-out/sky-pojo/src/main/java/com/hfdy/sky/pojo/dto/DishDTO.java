package com.hfdy.sky.pojo.dto;


import com.hfdy.sky.pojo.entity.DishFlavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@ApiModel("菜品")
public class DishDTO implements Serializable {
    
    @NotNull
    @ApiModelProperty("id")
    private Long id;

    @NotNull
    @ApiModelProperty("菜品名称")
    private String name;

    @NotNull
    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    @NotNull
    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    @NotNull
    @ApiModelProperty("图片")
    private String image;

    @NotNull
    @ApiModelProperty("描述信息")
    private String description;

    @NotNull
    @ApiModelProperty("0 停售 1 起售")
    private Integer status;

    @NotNull
    @ApiModelProperty("口味")
    private List<DishFlavor> flavors = new ArrayList<>();

}
