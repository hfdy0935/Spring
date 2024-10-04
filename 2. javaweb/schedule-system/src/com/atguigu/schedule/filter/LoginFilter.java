package com.atguigu.schedule.filter;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.utils.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/schedule/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object sysUser = session.getAttribute("sysUser");
        if (sysUser == null) {
            // 未登录
            Result<String> result = new Result<>(ResultCodeEnum.NOT_LOGIN, "还未登录");
            resp.setStatus(504);
            WebUtil.writeJSON(resp, result);
        } else {
            chain.doFilter(request, response);
        }
        // 设置跨域
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
    }
}
