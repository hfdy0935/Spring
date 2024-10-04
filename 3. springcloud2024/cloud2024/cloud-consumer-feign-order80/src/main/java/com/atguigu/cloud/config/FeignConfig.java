package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hf-dy
 * @date 2024/9/2 08:55
 */

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
//        return new Retryer.Default(100, 1, 5);
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
