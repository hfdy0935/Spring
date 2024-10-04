package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("查询分类请求参数")
public class CategoryDTO implements Serializable {
    @NotNull
    @ApiModelProperty("id")
    private Long id;

    @NotNull
    @ApiModelProperty("类型 1 菜品分类 2 套餐分类")
    private Integer type;

    @NotNull
    @ApiModelProperty("分类名称")
    private String name;

    @NotNull
    @ApiModelProperty("排序")
    private Integer sort;

}
