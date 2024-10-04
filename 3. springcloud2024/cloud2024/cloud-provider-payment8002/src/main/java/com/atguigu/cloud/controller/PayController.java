package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * @author hf-dy
 * @date 2024/8/31 10:32
 */

@RestController
@Slf4j
@RequestMapping("/pay")
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/test")
    private String getConsulTest(@Value("${atguigu.info}") String atguigu) {
        return "atguigu: " + atguigu + "\t" + "port: " + port;
    }

    @PostMapping
    @Operation(summary = "新增", description = "新增支付流水")
    public ResultData<String> addPay(@RequestBody PayDTO payDTO) {
//        System.out.println(pay.toString());
        int i = payService.add(payDTO);
        return ResultData.success("插入成功，返回值：" + i);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除", description = "删除支付流水")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping
    @Operation(summary = "修改", description = "修改支付流水")
    public ResultData<?> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return i == 0 ? ResultData.fail("403", "修改失败") : ResultData.success("修改成功，返回值：" + i);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取", description = "根据id获取支付流水")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch(InterruptedException e){
//            throw new RuntimeException(e);
//        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }
}
