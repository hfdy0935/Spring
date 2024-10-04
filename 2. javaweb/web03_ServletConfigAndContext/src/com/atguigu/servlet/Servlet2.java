package com.atguigu.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Enumeration;

/**
 * 注解方式配置
 */
@WebServlet(
        value = "/servlet2",
        initParams = {@WebInitParam(name = "encoding", value = "utf-8"), @WebInitParam(name = "keyA", value = "keyA的值")}
)
public class Servlet2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        // 获取xml中该类对应的初始配置信息
        ServletConfig config = this.getServletConfig();
        String value1 = config.getInitParameter("encoding"); // 参数名
        System.out.println("encoding=" + value1); // encoding=utf-8
        // 获取所有配置
        Enumeration<String> params = config.getInitParameterNames();
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String value = config.getInitParameter(key);
            System.out.println(key + "=" + value);
        }
        // keyA=keyA的值
        //encoding=utf-8
    }
}