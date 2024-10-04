package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/3 11:52
 */

@RestController
public class GatewayInfoController {
    @Resource
    PayFeignApi payFeignApi;

    @GetMapping("/feign/gateway/info")
    public ResultData<String> getGatewayInfo(@RequestParam(value = "userType", defaultValue = "") String userType) {
        return payFeignApi.getGatewayInfo(userType);
    }
}
