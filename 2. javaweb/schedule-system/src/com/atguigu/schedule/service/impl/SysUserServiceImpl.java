package com.atguigu.schedule.service.impl;

import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.dao.impl.SysUserDaoImpl;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.utils.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    private final SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) {
        // 密码加密
        sysUser.setPassword(MD5Util.encrypt(sysUser.getPassword()));
        // 存入数据库
        return sysUserDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findUserByName(String username) {
        return sysUserDao.findUserByName(username);
    }

    @Override
    public int update(SysUser sysUser) {
        sysUser.setPassword(MD5Util.encrypt(sysUser.getPassword()));
        System.out.println(sysUser.getPassword());
        return sysUserDao.update(sysUser);
    }

    @Override
    public int deleteByUid(int uid) {
        return sysUserDao.deleteByUid(uid);
    }
}
