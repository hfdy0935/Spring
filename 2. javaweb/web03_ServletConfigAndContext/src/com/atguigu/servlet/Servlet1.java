package com.atguigu.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;

public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//        ============================== ServletConfig ==============================
        // 1. 获取xml中 该类 对应的初始配置信息
        ServletConfig config = this.getServletConfig();
        String value1 = config.getInitParameter("keyA"); // 参数名
        System.out.println("keyA=" + value1);
        // 获取所有配置
        Enumeration<String> params = config.getInitParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value2 = config.getInitParameter(key);
            System.out.println(key + "=" + value2);
        }
        System.out.println("======================================================");

//        ============================== ServletContext ==============================
        // 2. 获取全局配置
        ServletContext context = this.getServletContext();
        String value3 = context.getInitParameter("globalParam1");
        System.out.println("globalParam1=" + value3); // 我是servletContext配置的全局参数1
        // 获取所有全局配置
        Enumeration<String> params1 = context.getInitParameterNames();
        while (params1.hasMoreElements()) {
            String key = params1.nextElement();
            String value4 = context.getInitParameter(key);
            System.out.println(key + "=" + value4);
        }

        System.out.println("======================================================");
        // 输入资源在项目中的路径，获取对应的磁盘路径
        System.out.println(context.getRealPath("com.atguigu.servlet.Servlet1.java")); // D:\桌面\t\Java\tomcat_test\web_app\out\artifacts\web03_ServletConfigAndContext_war_exploded\com.atguigu.servlet.Servlet1.java
        // 获取项目的上下文路径
        System.out.println(context.getContextPath()); // /web03_ServletConfigAndContext_war_exploded
        // ServletContext是应用域，属于webapp的三大域对象（应用域、会话域、请求域）之一；
        // 这三者都可以获取、设置、移除属性；
        // void setAttribute(String key, Object value)
        // Object getAttribute(String key)
        // void removeAttribute(String key)
    }
}