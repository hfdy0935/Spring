package com.atguigu.schedule.test;

import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import org.junit.Test;

public class TestSysUserDao {
    private static final SysUserService sysUserService = new SysUserServiceImpl();

    @Test
    public void test() {
        // 增
//        System.out.println(sysUserService.register(new SysUser(null, "田七", "123456"))); // 1
        // 查
        SysUser user = sysUserService.findUserByName("张三");
//        System.out.println(user.getPassword()); // 123456zs
        // 改
        SysUser newUser = new SysUser(user.getUid(), user.getUsername(), "123456zsa");
//        System.out.println(sysUserService.update(newUser)); // 1
        // 删
        System.out.println(sysUserService.deleteByUid(4)); // 1
    }
}
