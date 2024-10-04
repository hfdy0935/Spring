package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("订单分页查询请求体")
public class OrderPageQueryDTO implements Serializable {

    @NotNull
    @ApiModelProperty("页码数")
    private int page;

    @NotNull
    @ApiModelProperty("每页数量")
    private int pageSize;

    @ApiModelProperty("订单号")
    private String number;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("订单状态")
    private Integer status;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("用户id")
    private Long userId;

}
