package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hf-dy
 * @date 2024/9/4 11:02
 */

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE() {
        return "------testE";
    }

    /**
     * 新增熔断规则-慢调用比例
     *
     * @return
     */
    @GetMapping("/testF")
    public String testF() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testF 新增熔断规则-慢调用比例";
    }
}
