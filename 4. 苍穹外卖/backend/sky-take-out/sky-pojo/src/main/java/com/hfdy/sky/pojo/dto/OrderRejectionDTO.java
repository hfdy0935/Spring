package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("拒单请求体")
public class OrderRejectionDTO implements Serializable {

    @NotNull
    @ApiModelProperty("订单id")
    private Long id;

    @NotNull
    @ApiModelProperty("订单拒绝原因")
    private String rejectionReason;

}
