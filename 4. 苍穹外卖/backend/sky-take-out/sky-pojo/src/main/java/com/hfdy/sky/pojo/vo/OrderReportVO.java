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
@ApiModel("订单统计")
public class OrderReportVO implements Serializable {

    @ApiModelProperty("日期，以逗号分隔，例如：2022-10-01,2022-10-02,2022-10-03")
    private String dateList;

    @ApiModelProperty("每日订单数，以逗号分隔，例如：260,210,215")
    private String orderCountList;

    @ApiModelProperty("每日有效订单数，以逗号分隔，例如：20,21,10")
    private String validOrderCountList;

    @ApiModelProperty("订单总数")
    private Integer totalOrderCount;

    @ApiModelProperty("有效订单数")
    private Integer validOrderCount;

    @ApiModelProperty("订单完成率")
    private Double orderCompletionRate;

}
