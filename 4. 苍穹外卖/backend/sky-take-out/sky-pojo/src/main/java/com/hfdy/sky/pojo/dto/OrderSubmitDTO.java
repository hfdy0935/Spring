package com.hfdy.sky.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel("提交订单请求体")
public class OrderSubmitDTO implements Serializable {

    @NotNull
    @ApiModelProperty("地址簿id")
    private Long addressBookId;

    @NotNull
    @ApiModelProperty("付款方式")
    private int payMethod;

    @ApiModelProperty("备注")
    private String remark;

    // 前端传来字符串不规范
    @ApiModelProperty("预计送达时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd :HH")
    private LocalDateTime estimatedDeliveryTime;

    @ApiModelProperty("配送状态  1立即送出  0选择具体时间")
    private Integer deliveryStatus;

    @ApiModelProperty("餐具数量")
    private Integer tablewareNumber;

    @ApiModelProperty("餐具数量状态  1按餐量提供  0选择具体数量")
    private Integer tablewareStatus;

    @ApiModelProperty("打包费")
    private Integer packAmount;

    @ApiModelProperty("总金额")
    private BigDecimal amount;
}
