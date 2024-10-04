package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {
    /**
     * 添加
     *
     * @param sysSchedule SysSchedule
     * @return 影响行数
     */
    int add(SysSchedule sysSchedule);

    /**
     * 查询该用户所有的schedule
     *
     * @return List<SysSchedule>
     */
    List<SysSchedule> findAll(int uid);

    /**
     * 查找该用户的第一个sysSchedule
     *
     * @param uid uid
     * @return SysSchedule
     */
    SysSchedule findOne(int uid);

    /**
     * 更新
     *
     * @param sysSchedule SysSchedule
     * @return 影响行数
     */
    int update(SysSchedule sysSchedule);

    /**
     * 根据sid删除
     *
     * @param sid int
     * @return 影响行数
     */
    int deleteBySID(int sid);
}
