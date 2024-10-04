package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/2 11:48
 */

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        return payFeignApi.myCircuit(id);
    }

    // 服务降级后的兜底处理方法
    private String myCircuitFallback(Integer id, Throwable t) {
        System.out.println(t);
        return "myCircuitFallback，系统繁忙，请稍后再试...";
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public String myBulkhead(@PathVariable("id") Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadFallback(Throwable t) {
        return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
    }

    @GetMapping(value = "/feign/pay/ratelimit/{id}")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRatelimitFallback")
    public String myBulkhead1(@PathVariable("id") Integer id) {
        return payFeignApi.myRatelimit(id);
    }

    public String myRatelimitFallback(Integer id, Throwable t) {
        return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
    }
}
