package com.atguigu.schedule.service.impl;

import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.dao.impl.SysScheduleDaoImpl;
import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private static final SysScheduleDao sysScheduleDao = new SysScheduleDaoImpl();

    @Override
    public int add(SysSchedule sysSchedule) {
        return sysScheduleDao.add(sysSchedule);
    }

    @Override
    public List<SysSchedule> findAll(int uid) {
        return sysScheduleDao.findAll(uid);
    }

    @Override
    public SysSchedule findOne(int uid) {
        return sysScheduleDao.findOne(uid);
    }

    @Override
    public int update(SysSchedule sysSchedule) {
        return sysScheduleDao.update(sysSchedule);
    }


    @Override
    public int deleteBySID(int sid) {
        return sysScheduleDao.deleteBySID(sid);
    }
}
