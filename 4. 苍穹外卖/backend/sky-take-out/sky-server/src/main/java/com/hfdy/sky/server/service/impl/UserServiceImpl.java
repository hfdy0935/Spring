package com.hfdy.sky.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfdy.sky.common.constant.MessageConstant;
import com.hfdy.sky.common.exception.LoginFailedException;
import com.hfdy.sky.common.properties.WeChatProperties;
import com.hfdy.sky.common.utils.HttpClientUtil;
import com.hfdy.sky.pojo.dto.UserLoginDTO;
import com.hfdy.sky.pojo.entity.User;
import com.hfdy.sky.server.mapper.UserMapper;
import com.hfdy.sky.server.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hf-dy
 * @date 2024/9/30 22:41
 */

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private UserMapper userMapper;
    @Resource
    private WeChatProperties weChatProperties;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 授权码
        String code = userLoginDTO.getCode();
        String openid = getOpenid(code);
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 根据openid获取用户信息
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getOpenid, openid);
        User user = userMapper.selectOne(userLambdaQueryWrapper);

        // 新用户，注册
        if (user == null) {
            user = User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);
        }
        return user;
    }

    /**
     * 获取微信用户的openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        // 请求参数
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        // 发送请求
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        log.info("微信登陆返回结果：{}", json);
        // 解析
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        log.info("微信用户的openid为：{}", openid);
        return openid;
    }
}
