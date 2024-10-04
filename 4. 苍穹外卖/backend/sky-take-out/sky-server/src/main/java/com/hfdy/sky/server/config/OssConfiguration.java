package com.hfdy.sky.server.config;

import com.hfdy.sky.common.properties.AliOssProperties;
import com.hfdy.sky.common.utils.AliOssUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hf-dy
 * @date 2024/9/29 16:56
 */
@Configuration
@Slf4j
public class OssConfiguration {
    @Resource
    private AliOssProperties aliOssProperties;

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil() {
        log.info("开始创建阿里云OOS工具类");
        return new AliOssUtil(
                aliOssProperties.getEndpoint(),
                aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(),
                aliOssProperties.getBucketName());
    }
}
