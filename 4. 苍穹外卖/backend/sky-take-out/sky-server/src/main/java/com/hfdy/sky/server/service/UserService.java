package com.hfdy.sky.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfdy.sky.pojo.dto.UserLoginDTO;
import com.hfdy.sky.pojo.entity.User;

/**
 * @author hf-dy
 * @date 2024/9/30 22:40
 */

public interface UserService extends IService<User> {

    /**
     * 用户从微信登录
     *
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
