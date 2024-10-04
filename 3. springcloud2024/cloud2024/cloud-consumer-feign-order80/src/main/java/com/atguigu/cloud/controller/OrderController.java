package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author hf-dy
 * @date 2024/9/1 22:25
 */

@RestController
@RequestMapping("/feign")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping
    public ResultData<String> addPay(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @DeleteMapping("{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        return payFeignApi.deletePay(id);
    }

    @PutMapping
    public ResultData<?> updatePay(@RequestBody PayDTO payDTO) {
        return payFeignApi.updatePay(payDTO);
    }

    @GetMapping("{id}")
    public ResultData<PayDTO> getById(@PathVariable("id") Integer id) {
        ResultData<PayDTO> resultData = null;
        try {
            System.out.println("调用开始，" + DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            System.out.println("调用结束，" + DateUtil.now());
            throw new RuntimeException(e);
        }
        return resultData;
    }

    @GetMapping("/test")
    public String getConsulTest() {
        return payFeignApi.getConsulTest();
    }
}
