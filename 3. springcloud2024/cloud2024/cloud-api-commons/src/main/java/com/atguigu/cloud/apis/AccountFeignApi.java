package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hf-dy
 * @date 2024/9/4 21:24
 */

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {
    //扣减账户余额
    @PostMapping("/account/decrease")
    ResultData<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
