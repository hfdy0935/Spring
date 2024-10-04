package com.atguigu.schedule.utils;

import com.atguigu.schedule.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class WebUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy=MM-dd HH:mm:ss"));
    }

    /**
     * 从HttpServletRequest读取json
     *
     * @param req   从HttpServletRequest读取json
     * @param clazz 结果类
     * @param <T>   T
     * @return T
     */
    public static <T> T readJSON(HttpServletRequest req, Class<T> clazz) {
        T t = null;
        BufferedReader reader = null;
        try {
            reader = req.getReader();
            StringBuilder buffer = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            t = objectMapper.readValue(buffer.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }


    /**
     * Result实例转为json并返回响应
     *
     * @param resp   HttpServletResponse
     * @param result Result
     */
    public static <T> void writeJSON(HttpServletResponse resp, Result<T> result) {
        resp.setContentType("application/json;charset=utf-8");
        try {
            String json = objectMapper.writeValueAsString(result);
            resp.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
