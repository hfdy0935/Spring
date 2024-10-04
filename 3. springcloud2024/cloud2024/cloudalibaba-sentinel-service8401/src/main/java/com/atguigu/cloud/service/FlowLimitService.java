package com.atguigu.cloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author hf-dy
 * @date 2024/9/4 11:25
 */

@Service
public class FlowLimitService {
    @SentinelResource(value = "common")
    public void common() {
        System.out.println("------FlowLimitService come in");
    }
}
