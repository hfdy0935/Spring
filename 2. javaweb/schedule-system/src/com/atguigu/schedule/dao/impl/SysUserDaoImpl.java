package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.pojo.SysUser;

public class SysUserDaoImpl extends BaseDao<SysUser> implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return this.update(sql, sysUser.getUsername(), sysUser.getPassword());
    }

    @Override
    public SysUser findUserByName(String username) {
        String sql = "select * from sys_user where username=?";
        return this.findOne(SysUser.class, sql, username);
    }

    @Override
    public int update(SysUser sysUser) {
        String sql = "update sys_user set username=?,password=? where uid=?";
        return this.update(sql, sysUser.getUsername(), sysUser.getPassword(), sysUser.getUid());
    }

    @Override
    public int deleteByUid(int uid) {
        String dql = "delete from sys_user where uid=?";
        return this.update(dql, uid);
    }
}
