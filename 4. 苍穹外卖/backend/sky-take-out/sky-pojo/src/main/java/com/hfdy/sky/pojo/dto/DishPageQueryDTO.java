package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("菜品分页查询请求参数")
public class DishPageQueryDTO implements Serializable {

    @NotNull
    @ApiModelProperty("页数")
    private int page;

    @NotNull
    @ApiModelProperty("数量")
    private int pageSize;

    @ApiModelProperty("菜品名")
    private String name;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("状态 0表示禁用 1表示启用")
    private Integer status;

}
