package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysScheduleService;
import com.atguigu.schedule.service.impl.SysScheduleServiceImpl;
import com.atguigu.schedule.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    private static final SysScheduleService sysScheduleService = new SysScheduleServiceImpl();

    // 添加
    public void add(HttpServletRequest req, HttpServletResponse resp) {
        SysSchedule schedule = WebUtil.readJSON(req, SysSchedule.class);
        Result<String> result = null;
        int rows = sysScheduleService.add(schedule);
        if (rows > 0) {
            result = new Result<>(ResultCodeEnum.SUCCESS, "添加成功");
        } else {
            result = new Result<>(ResultCodeEnum.FAIL, "添加失败");
        }
        WebUtil.writeJSON(resp, result);
    }

    // 更新
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        SysSchedule schedule = WebUtil.readJSON(req, SysSchedule.class);
        Result<String> result = null;
        System.out.println(schedule);
        int rows = sysScheduleService.update(schedule);
        if (rows > 0) {
            result = new Result<>(ResultCodeEnum.SUCCESS, "更新成功");
        } else {
            result = new Result<>(ResultCodeEnum.FAIL, "更新失败");
        }
        WebUtil.writeJSON(resp, result);
    }


    // 获取该用户所有的schedule
    public void getAll(HttpServletRequest req, HttpServletResponse resp) {
        int uid = Integer.parseInt(req.getParameter("uid"));
        List<SysSchedule> sysScheduleList = sysScheduleService.findAll(uid);
        if (sysScheduleList.isEmpty()) {
            Result<String> result = new Result<>(ResultCodeEnum.SUCCESS, "");
            WebUtil.writeJSON(resp, result);
        } else {
            Result<List<SysSchedule>> result = new Result<>(ResultCodeEnum.SUCCESS, sysScheduleList);
            WebUtil.writeJSON(resp, result);
        }
    }

    // 删除
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        int sid = Integer.parseInt(req.getParameter("sid"));
        System.out.println(sid);
        Result<String> result = null;
        int rows = sysScheduleService.deleteBySID(sid);
        if (rows > 0) {
            result = new Result<>(ResultCodeEnum.SUCCESS, "删除成功");
        } else {
            result = new Result<>(ResultCodeEnum.FAIL, "删除失败");
        }
        WebUtil.writeJSON(resp, result);
    }
}
