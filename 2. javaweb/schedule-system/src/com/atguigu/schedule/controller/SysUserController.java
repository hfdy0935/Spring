package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.ChangePassword;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.utils.MD5Util;
import com.atguigu.schedule.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class SysUserController extends BaseController {
    private final SysUserService sysUserService = new SysUserServiceImpl();

    // 注册
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取提交的参数
        SysUser user = WebUtil.readJSON(req, SysUser.class);
        String username = user.getUsername();
        String password = user.getPassword();
        // 如果用户已存在
        if (sysUserService.findUserByName(username) != null) {
            Result<String> result = null;
            result = new Result<>(ResultCodeEnum.FAIL, "用户已存在");
            WebUtil.writeJSON(resp, result);
        } else {
            // 调用业务层，完成注册
            SysUser sysUser = new SysUser(null, username, password);
            int rows = sysUserService.register(sysUser);
            if (rows > 0) {
                Result<SysUser> result = new Result<>(ResultCodeEnum.SUCCESS, sysUser);
                req.getSession().setAttribute("sysUser", true);
                WebUtil.writeJSON(resp, result);
            } else {
                Result<String> result = null;
                result = new Result<>(ResultCodeEnum.FAIL, "注册失败");
                WebUtil.writeJSON(resp, result);
            }
        }
    }

    //登录
    private void login(HttpServletRequest req, HttpServletResponse resp) {
        SysUser user = WebUtil.readJSON(req, SysUser.class);
        String username = user.getUsername();
        String password = user.getPassword();
        // 查询用户信息
        SysUser sysUser = sysUserService.findUserByName(username);
        if (sysUser == null) {
            // 还未注册
            Result<String> result = new Result<>(ResultCodeEnum.FAIL, "还未注册");
            WebUtil.writeJSON(resp, result);
        } else if (!MD5Util.encrypt(password).equals(sysUser.getPassword())) {
            // 密码错误
            Result<String> result = new Result<>(ResultCodeEnum.FAIL, "密码错误");
            WebUtil.writeJSON(resp, result);

        } else {
            Result<SysUser> result = new Result<>(ResultCodeEnum.SUCCESS, sysUser);
            req.getSession().setAttribute("sysUser", true);
            WebUtil.writeJSON(resp, result);
        }
    }

    // 修改密码
    private void changePassword(HttpServletRequest req, HttpServletResponse resp) {
        ChangePassword user = WebUtil.readJSON(req, ChangePassword.class);
        String username = user.getUsername();
        String oldPassword = user.getOldPassword();
        String newPassword = user.getNewPassword();
        Result<String> result = null;
        // 匹配用户名旧密码
        SysUser sysUser = sysUserService.findUserByName(username);
        if (sysUser == null) {
            // 还未注册
            result = new Result<>(ResultCodeEnum.FAIL, "还未注册");
            resp.setStatus(500);
        } else if (!MD5Util.encrypt(oldPassword).equals(sysUser.getPassword())) {
            // 旧密码错误
            result = new Result<>(ResultCodeEnum.FAIL, "旧密码错误");
            resp.setStatus(500);
        } else {
            sysUser.setPassword(newPassword);
            sysUserService.update(sysUser);
            result = new Result<>(ResultCodeEnum.SUCCESS, "修改成功");
        }
        WebUtil.writeJSON(resp, result);
    }
}
