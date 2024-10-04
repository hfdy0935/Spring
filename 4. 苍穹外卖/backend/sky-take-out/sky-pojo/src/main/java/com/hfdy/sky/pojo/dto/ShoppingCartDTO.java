package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("提交购物车模型")
public class ShoppingCartDTO implements Serializable {

    @ApiModelProperty("菜品id")
    private Long dishId;
    
    @ApiModelProperty("套餐id")
    private Long setmealId;
    @NotNull
    @ApiModelProperty("所选口味")
    private String dishFlavor;

}
