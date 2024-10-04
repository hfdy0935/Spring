package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    public UserServlet() {
        System.out.println("构造器");
    }

    @Override
    public void init() {
        System.out.println("初始化");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理请求");
        String username = req.getParameter("username");
        PrintWriter writer = resp.getWriter();
        if ("atguigu".equals(username)) {
            writer.write("NO");
        } else {
            writer.write("YES");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("处理请求");
//        String username = req.getParameter("username");
//        PrintWriter writer = resp.getWriter();
//        if ("atguigu".equals(username)) {
//            writer.write("NO");
//        } else {
//            writer.write("YES");
//        }
//    }

    @Override
    public void destroy() {
        // 结束服务前会打印
        System.out.println("销毁");
    }
}
