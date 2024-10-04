package com.atguigu.schedule.test;

import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.service.SysScheduleService;
import com.atguigu.schedule.service.impl.SysScheduleServiceImpl;
import org.junit.Test;

import java.util.List;

public class TestScheduleDao {
    private static final SysScheduleService sysScheduleService = new SysScheduleServiceImpl();

    /**
     * 添加测试
     */
    @Test
    public void addTest() {
        // 给张三添加
        SysSchedule sysSchedule = new SysSchedule(null, 1, "学习java", 0);
        int rows = sysScheduleService.add(sysSchedule);
        System.out.println(rows);
    }

    /**
     * 查询测试
     */
    @Test
    public void selectTest() {
        // 查询张三所有的schedule
        List<SysSchedule> sysScheduleList = sysScheduleService.findAll(1);
        for (SysSchedule sysSchedule : sysScheduleList) {
            System.out.println(sysSchedule);
        }
    }

    /**
     * 修改测试
     */
    @Test
    public void updateTest() {
        // 查询张三第一个schedule
        SysSchedule sysSchedule = sysScheduleService.findOne(1);
        if (sysSchedule != null) {
            sysSchedule.setCompleted(sysSchedule.getCompleted() == 0 ? 1 : 0);
            int rows = sysScheduleService.update(sysSchedule);
            System.out.println(rows);
        }
    }

    /**
     * 删除测试
     */
    @Test
    public void deleteTest() {
        SysSchedule sysSchedule = sysScheduleService.findOne(1);
        if (sysSchedule != null) {
            int rows = sysScheduleService.deleteBySID(sysSchedule.getSid());
            System.out.println(rows);
        }
    }
}
