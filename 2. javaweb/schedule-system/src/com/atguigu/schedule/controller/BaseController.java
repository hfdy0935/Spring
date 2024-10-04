package com.atguigu.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] URISplitArr = req.getRequestURI().split("/");
        String methodName = URISplitArr[URISplitArr.length - 1];
        // 通过反射获取要执行的方法
        Class<? extends BaseController> clazz = this.getClass();
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
