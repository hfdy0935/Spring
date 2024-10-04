package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("分页查询员工信息的参数")
public class EmployeePageQueryDTO implements Serializable {

    @NotNull
    @ApiModelProperty("姓名")
    private String name;

    @NotNull
    @ApiModelProperty("页码数")
    private int page;

    @NotNull
    @ApiModelProperty("每页显示记录数")
    private int pageSize;

}
