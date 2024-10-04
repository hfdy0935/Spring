package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel("订单支付方式")
public class OrderPaymentDTO implements Serializable {


    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("付款方式")
    private Integer payMethod;

}
