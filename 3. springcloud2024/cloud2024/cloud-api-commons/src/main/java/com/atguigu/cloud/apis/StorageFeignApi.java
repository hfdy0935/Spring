package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hf-dy
 * @date 2024/9/4 21:23
 */

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {
    @PostMapping("/storage/decrease")
    ResultData<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
