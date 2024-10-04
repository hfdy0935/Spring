package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hf-dy
 * @date 2024/9/1 21:57
 */

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
@EnableFeignClients // 开启激活OpenFeign功能
public class MainOpenFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(MainOpenFeign80.class, args);
    }
}