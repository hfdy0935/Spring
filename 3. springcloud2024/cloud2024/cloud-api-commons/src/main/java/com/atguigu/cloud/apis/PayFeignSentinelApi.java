package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.fallback.PayFeignSentinelApiFallback;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hf-dy
 * @date 2024/9/4 16:03
 */
@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallback.class)
public interface PayFeignSentinelApi {
    @GetMapping("/pay/nacos/sentinel/{orderNo}")
    ResultData<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}