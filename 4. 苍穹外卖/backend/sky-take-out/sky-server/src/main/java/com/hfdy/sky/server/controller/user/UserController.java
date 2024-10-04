package com.hfdy.sky.server.controller.user;

import com.hfdy.sky.common.constant.JwtClaimsConstant;
import com.hfdy.sky.common.properties.JwtProperties;
import com.hfdy.sky.common.result.ApiResult;
import com.hfdy.sky.common.utils.JwtUtil;
import com.hfdy.sky.pojo.dto.UserLoginDTO;
import com.hfdy.sky.pojo.entity.User;
import com.hfdy.sky.pojo.vo.UserLoginVO;
import com.hfdy.sky.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hf-dy
 * @date 2024/9/30 22:37
 */
@RestController
@RequestMapping("/user/user")
@Slf4j
@Api("C端-用户接口")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation("登录")
    public ApiResult<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("微信用户登录：{}", userLoginDTO.getCode());
        User user = userService.wxLogin(userLoginDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return ApiResult.success(userLoginVO);
    }
}
