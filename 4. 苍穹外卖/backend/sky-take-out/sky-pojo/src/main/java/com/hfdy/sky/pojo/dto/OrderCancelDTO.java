package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("取消订单请求体")
public class OrderCancelDTO implements Serializable {

    @NotNull
    @ApiModelProperty("订单id")
    private Long id;

    @NotNull
    @ApiModelProperty("订单取消原因")
    private String cancelReason;

}
