package com.atguigu.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hf-dy
 * @date 2024/9/2 11:26
 */

@RestController
public class PayCircuitController {
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("----circuit id 不能为负数");
        } else if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    @GetMapping("/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id) {
        if (id == -4) {
            throw new RuntimeException("----circuit id 不能为负数");
        } else if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        return "Hello, circuit! inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }

    //=========Resilience4j ratelimit 的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    public String myRateLimit(@PathVariable("id") Integer id) {
        return "Hello, myRateLimit欢迎到来 inputId:  " + id + " \t " + IdUtil.simpleUUID();
    }
}
