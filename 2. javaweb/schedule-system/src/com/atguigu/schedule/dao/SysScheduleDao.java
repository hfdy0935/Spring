package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
    /**
     * 添加一条记录
     *
     * @param sysSchedule SysSchedule实例
     * @return 影响行数，0表示插入失败，1表示插入成功
     */
    int add(SysSchedule sysSchedule);

    /**
     * 查找该用户所有的sysSchedule
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
     * 修改
     *
     * @param sysSchedule SysSchedule
     * @return 影响行数
     */
    int update(SysSchedule sysSchedule);

    /**
     * 通过日程sid删除
     *
     * @param sid sid
     * @return 影响行数
     */
    int deleteBySID(int sid);
}
