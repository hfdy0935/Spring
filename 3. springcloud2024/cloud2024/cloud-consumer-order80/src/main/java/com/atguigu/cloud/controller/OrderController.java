package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author hf-dy
 * @date 2024/8/31 17:41
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    public static final String PaymentSrv_URL = "http://cloud-provider-payment/pay";

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public String getPayInfo() {
        List<String> serviceList = discoveryClient.getServices(); // 服务名

        // 根据名获取实例
        serviceList.forEach(name -> {
            List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(name);
            System.out.println("服务 " + name + "下共有以下微服务模块：");
            if (serviceInstanceList != null && !serviceInstanceList.isEmpty()) {
                serviceInstanceList.forEach(s -> {
                    System.out.println(s.getUri() + "  " + s.getPort());
                });
            } else {
                System.out.println("啥也没有");
            }
        });

        return restTemplate.getForObject(PaymentSrv_URL + "/test", String.class);
    }

    @PostMapping
    public ResultData<?> addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL, payDTO, ResultData.class);
    }

    @DeleteMapping("/{id}")
    public ResultData<Integer> deleteOrder(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL + "/" + id, ResultData.class);
        return ResultData.success(1);
    }

    @PutMapping
    public ResultData<String> updateOrder(@RequestBody PayDTO payDTO) {
        System.out.println(payDTO);
        restTemplate.put(PaymentSrv_URL, payDTO, ResultData.class);
        return ResultData.success("修改成功，返回值：1");
    }

    @GetMapping("/{id}")
    public ResultData<?> getById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/" + id, ResultData.class, id);
    }
}
