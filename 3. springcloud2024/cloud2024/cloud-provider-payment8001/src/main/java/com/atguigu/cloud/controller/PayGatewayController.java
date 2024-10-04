package com.atguigu.cloud.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * @author hf-dy
 * @date 2024/9/3 11:29
 */

@RestController
public class PayGatewayController {
    @Resource
    private PayService payService;

    @GetMapping("/pay/gateway/{id}")
    public ResultData<PayDTO> getById(@PathVariable("id") Integer id) {
        PayDTO payDTO = payService.getById(id);
        return ResultData.success(payDTO);
    }

    @GetMapping("/pay/gateway/info")
    public ResultData<String> getGatewayInfo(@RequestParam(value = "userType", defaultValue = "") String userType) {
        return ResultData.success("gateway info is " + IdUtil.simpleUUID());
    }

    /**
     * 返回所有请求头，测试在网关中添加请求头这里能否收到
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
        // 请求头
//        String result = "";
//        Enumeration<String> headers = request.getHeaderNames();
//        while (headers.hasMoreElements()) {
//            String headName = headers.nextElement();
//            String headValue = request.getHeader(headName);
//            System.out.println("请求头名: " + headName + "\t\t\t" + "请求头值: " + headValue);
//            if (headName.equalsIgnoreCase("X-Request-atguigu1")
//                    || headName.equalsIgnoreCase("X-Request-atguigu2")) {
//                result = result + headName + "\t " + headValue + " ";
//            }
//        }

        // 查询参数
//        String result = "";
//        Enumeration<String> params = request.getParameterNames();
//        while (params.hasMoreElements()) {
//            String name = params.nextElement();
//            String headValue = request.getParameter(name);
//            System.out.println("查询参数名: " + name + "\t\t\t" + "查询参数值: " + headValue);
//            if (name.equalsIgnoreCase("X-Request-atguigu1")
//                    || name.equalsIgnoreCase("X-Request-atguigu2")) {
//                result = result + name + "\t " + headValue + " ";
//            }
//        }
//        return ResultData.success("getGatewayFilter 过滤器 test： " + result + " \t " + DateUtil.now());

        // 响应头
        System.out.println(response.getHeaderNames());
        Collection<String> headers = response.getHeaderNames();
        for (String k : headers) {
            System.out.println("name = " + k + ", value = " + response.getHeader(k));
        }
        return ResultData.success("success");
    }
}
