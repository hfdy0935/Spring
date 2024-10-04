package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.serivce.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2024-01-06 16:16
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public ResultData<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
//        try {
//            TimeUnit.SECONDS.sleep(65);
//        } catch (InterruptedException e) {
//            System.out.println(e.getMessage());
//        }
        accountService.decrease(userId, money);
        return ResultData.success("扣减账户余额成功！");
    }
}
