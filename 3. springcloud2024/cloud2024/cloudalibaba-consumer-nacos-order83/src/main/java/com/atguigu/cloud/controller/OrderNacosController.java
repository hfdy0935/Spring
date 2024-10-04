package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignSentinelApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hf-dy
 * @date 2024/9/4 09:07
 */
@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {
        String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
        return result + "\t" + "    我是OrderNacosController83调用者。。。。。。";
    }


    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;

    @GetMapping("/consumer/pay/nacos/sentinel/{id}")
    public ResultData<PayDTO> getPayByOrderNo(@PathVariable("id") String id) {
        return payFeignSentinelApi.getPayByOrderNo(id);
    }
}