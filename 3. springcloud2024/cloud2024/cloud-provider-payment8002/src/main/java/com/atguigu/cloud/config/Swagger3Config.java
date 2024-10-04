package com.atguigu.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hf-dy
 * @date 2024/8/31 11:58
 */
@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi OtherApi() {
        return GroupedOpenApi.builder().group("其他微服务模块").pathsToMatch("/other/**", "/othersl").build();
    }

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("cloud2024")
                        .description("通用设计rest")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.atguigu.com")
                        .url("https://yiyan.baidu.com/"));
    }
}
