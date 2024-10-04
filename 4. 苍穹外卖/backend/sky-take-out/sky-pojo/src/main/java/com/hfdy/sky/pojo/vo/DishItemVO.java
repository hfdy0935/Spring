package com.hfdy.sky.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("菜品")
public class DishItemVO implements Serializable {

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("份数")
    private Integer copies;

    @ApiModelProperty("菜品图片")
    private String image;

    @ApiModelProperty("菜品描述")
    private String description;
}
