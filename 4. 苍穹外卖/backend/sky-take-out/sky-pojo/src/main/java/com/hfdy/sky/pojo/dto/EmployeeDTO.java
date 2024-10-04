package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel("员工")
public class EmployeeDTO implements Serializable {

    @NotNull
    @ApiModelProperty("员工id")
    private Long id;

    @NotNull
    @ApiModelProperty("员工用户名")
    private String username;

    @NotNull
    @ApiModelProperty("员工姓名")
    private String name;

    @NotNull
    @ApiModelProperty("电话号码")
    private String phone;

    @NotNull
    @ApiModelProperty("性别")
    private String sex;

    @NotNull
    @ApiModelProperty("id")
    private String idNumber;

}
