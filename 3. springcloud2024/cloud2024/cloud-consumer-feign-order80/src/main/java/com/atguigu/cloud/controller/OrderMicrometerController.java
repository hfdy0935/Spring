package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/3 10:02
 */

@RestController
public class OrderMicrometerController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return payFeignApi.myMicrometer(id);
    }
}
