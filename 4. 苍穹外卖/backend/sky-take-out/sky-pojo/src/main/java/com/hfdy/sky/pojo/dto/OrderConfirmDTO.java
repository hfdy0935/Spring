package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("确认订单请求体")
public class OrderConfirmDTO implements Serializable {

    @NotNull
    @ApiModelProperty("订单id")
    private Long id;
    @ApiModelProperty("订单状态 1待付款 2待接单 3 已接单 4 派送中 5 已完成 6 已取消 7 退款")
    private Integer status;

}
