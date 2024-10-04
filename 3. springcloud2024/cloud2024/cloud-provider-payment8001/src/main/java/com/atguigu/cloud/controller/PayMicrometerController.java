package com.atguigu.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hf-dy
 * @date 2024/9/3 09:59
 */

@RestController
public class PayMicrometerController {
    @GetMapping("/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id) {
        return "Hello Micrometer";
    }
}
