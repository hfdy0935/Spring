package com.hfdy.sky.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
@ApiModel("订单统计响应体")
public class OrderStatisticsVO implements Serializable {

    @ApiModelProperty("待接单数量")
    private Integer toBeConfirmed;

    @ApiModelProperty("待派送数量")
    private Integer confirmed;

    @ApiModelProperty("派送中数量")
    private Integer deliveryInProgress;
}
