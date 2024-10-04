package com.hfdy.sky.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 菜品总览
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品总览")
public class DishOverViewVO implements Serializable {

    @ApiModelProperty("已启售数量")
    private Integer sold;

    @ApiModelProperty("已停售数量")
    private Integer discontinued;
}
