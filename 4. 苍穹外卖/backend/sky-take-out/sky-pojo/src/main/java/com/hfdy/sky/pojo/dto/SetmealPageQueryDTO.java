package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@ApiModel("套餐分页查询请求体")
public class SetmealPageQueryDTO implements Serializable {

    @NotNull
    @ApiModelProperty("页码")
    private int page;

    @NotNull
    @ApiModelProperty("一页的数量")
    private int pageSize;
    
    @ApiModelProperty("套餐名")
    private String name;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("状态 0表示禁用 1表示启用")
    private Integer status;

}
