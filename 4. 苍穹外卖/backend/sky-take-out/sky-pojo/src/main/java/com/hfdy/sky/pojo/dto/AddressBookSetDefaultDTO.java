package com.hfdy.sky.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author hf-dy
 * @date 2024/10/2 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("设置默认收货地址请求体")
public class AddressBookSetDefaultDTO {

    @NotNull
    @ApiModelProperty("收货地址id")
    private Long id;
}
