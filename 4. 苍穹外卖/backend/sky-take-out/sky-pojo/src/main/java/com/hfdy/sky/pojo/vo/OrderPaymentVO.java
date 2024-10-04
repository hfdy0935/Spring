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
@ApiModel("订单支付信息响应体")
public class OrderPaymentVO implements Serializable {

    @ApiModelProperty("随机字符串")
    private String nonceStr;

    @ApiModelProperty("签名")
    private String paySign;
    @ApiModelProperty("时间戳")
    private String timeStamp;

    @ApiModelProperty("签名算法")
    private String signType;

    @ApiModelProperty("统一下单接口返回的 prepay_id 参数值")
    private String packageStr;

}
