package com.atguigu.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class Servlet1 extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=======================请求行===========================");
        System.out.println(req.getRequestURL()); // url
        System.out.println(req.getRequestURI()); // 服务端的路径
        System.out.println(req.getLocalAddr()); // 0:0:0:0:0:0:0:1
        System.out.println(req.getServerPort()); // 8080
        System.out.println(req.getScheme()); // http
        System.out.println(req.getProtocol()); // http/1.1
        System.out.println(req.getMethod()); // GET

        System.out.println("=======================请求头===========================");
        System.out.println(req.getHeader("User-Agent")); // Mozilla/5.0 (Windows NT 10.0; Win64; x64)......
        System.out.println(req.getHeader("referer")); // http://localhost:8080/......
        // 所有请求头
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ":" + req.getHeader(headerName));
        }

        System.out.println("=======================请求参数===========================");
        // 如果相同key有多个值，返回数组
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));
        // 所有请求参数
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            System.out.println(paramName + ":" + req.getParameter(paramName));
        }
        // 所有请求参数键值对Map
        for (Map.Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue()[0]);
        }
        // 输入输出流
//        try (BufferedReader bis = req.getReader();
//             ServletInputStream sis = req.getInputStream();
//        ) {
//            // 字符输入流
//            // 字节输入流
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        // 请求体长度的字节数
        System.out.println(req.getContentLength());
        System.out.println(req.getContentLengthLong());
        // ...
    }
}
