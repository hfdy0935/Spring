package com.hfdy.sky.server;


import com.hfdy.sky.common.properties.AliOssProperties;
import com.hfdy.sky.common.properties.JwtProperties;
import com.hfdy.sky.common.properties.WeChatProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hf-dy
 * @date 2024/9/27 23:16
 */
@SpringBootApplication
@MapperScan("com.hfdy.sky.server.mapper") // 这个扫描路径没写到mapper，报错了一下午
@EnableConfigurationProperties({JwtProperties.class, AliOssProperties.class, WeChatProperties.class})
@EnableCaching
@EnableScheduling
public class SkyServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyServerApplication.class, args);
    }
}