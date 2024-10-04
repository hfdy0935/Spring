package com.hfdy.sky.pojo.vo;


import com.hfdy.sky.pojo.entity.OrderDetail;
import com.hfdy.sky.pojo.entity.Orders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("订单详情响应体")
public class OrderVO extends Orders implements Serializable {

    @ApiModelProperty("订单菜品信息")
    private String orderDishes;

    @ApiModelProperty("订单详情")
    private List<OrderDetail> orderDetailList;

}
