package com.hfdy.sky.server.interceptor;

import com.hfdy.sky.common.constant.JwtClaimsConstant;
import com.hfdy.sky.common.context.BaseContext;
import com.hfdy.sky.common.properties.JwtProperties;
import com.hfdy.sky.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author hf-dy
 * @date 2024/9/30 22:55
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 用户jwt校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(jwtProperties.getUserTokenName());
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

}
