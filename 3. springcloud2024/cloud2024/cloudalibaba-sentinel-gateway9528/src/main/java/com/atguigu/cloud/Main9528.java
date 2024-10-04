package com.atguigu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hf-dy
 * @date 2024/9/4 17:34
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Main9528 {
    public static void main(String[] args) {
        SpringApplication.run(Main9528.class, args);
    }
}