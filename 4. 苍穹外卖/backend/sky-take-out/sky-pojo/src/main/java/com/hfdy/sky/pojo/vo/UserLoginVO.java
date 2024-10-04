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
@ApiModel("用户登录响应模型")
public class UserLoginVO implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("从微信获取的用户唯一标识")
    private String openid;
    @ApiModelProperty("token")
    private String token;

}
