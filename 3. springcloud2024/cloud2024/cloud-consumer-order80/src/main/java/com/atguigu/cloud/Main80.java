package com.atguigu.cloud;


import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author hf-dy
 * @date 2024/8/31 16:50
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
public class Main80 {
    public static void main(String[] args) {
        SpringApplication.run(Main80.class, args);
    }

    @Resource
    RestTemplateBuilder restTemplateBuilder;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(@Autowired RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}