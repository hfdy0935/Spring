package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysUser;

public interface SysUserDao {
    /**
     * 添加一个用户
     * @param sysUser SysUser
     * @return 0失败，1成功
     */
    int addSysUser(SysUser sysUser);

    /**
     * 根据用户名找到用户
     * @param username 用户名
     * @return SysUser
     */
    SysUser findUserByName(String username);

    /**
     * 更新用户
     * @param sysUser SysUser
     * @return 0失败，1成功
     */
    int update(SysUser sysUser);

    /**
     * 根据用户id删除用户
     * @param uid 用户id
     * @return 0失败，1成功
     */
    int deleteByUid(int uid);
}
