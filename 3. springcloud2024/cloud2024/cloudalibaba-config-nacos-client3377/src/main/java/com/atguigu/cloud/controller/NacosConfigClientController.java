package com.atguigu.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/4 09:36
 */

@RestController
@RefreshScope //在控制器类加入@RefreshScope注解使当前类下的配置支持Nacos的动态刷新功能
public class NacosConfigClientController {
    @Value("${atguigu.info}")
    private String info;

    @GetMapping("/atguigu/info")
    public String getAtguiguInfo() {
        return info;
    }
}
