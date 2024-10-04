package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

/**
 *
 */
public class SysScheduleDaoImpl extends BaseDao<SysSchedule> implements SysScheduleDao {
    @Override
    public int add(SysSchedule schedule) {
        String sql = "insert into sys_schedule values(DEFAULT,?,?,?)";
        return this.update(sql, schedule.getUid(), schedule.getTitle(), schedule.getCompleted());
    }

    @Override
    public List<SysSchedule> findAll(int uid) {
        String sql = "select * from sys_schedule where uid = ?";
        return this.findMany(SysSchedule.class, sql, uid);
    }

    @Override
    public SysSchedule findOne(int uid) {
        String sql = "select * from sys_schedule where uid = ? limit 1";
        return this.findOne(SysSchedule.class, sql, uid);
    }

    @Override
    public int update(SysSchedule sysSchedule) {
        String sql = "update sys_schedule set title=?,completed=? where sid=?";
        return this.update(sql, sysSchedule.getTitle(), sysSchedule.getCompleted(), sysSchedule.getSid());
    }

    @Override
    public int deleteBySID(int sid) {
        String sql = "delete from sys_schedule where sid=?";
        return this.update(sql, sid);
    }
}
