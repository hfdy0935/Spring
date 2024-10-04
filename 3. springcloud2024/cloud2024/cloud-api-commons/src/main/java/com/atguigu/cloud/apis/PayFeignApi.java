package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author hf-dy
 * @date 2024/9/1 22:07
 */
//cloud-provider-payment
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {
    @PostMapping("/pay")
    ResultData<String> addPay(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/{id}")
    ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    @PutMapping("/pay")
    ResultData<?> updatePay(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/{id}")
    ResultData<PayDTO> getById(@PathVariable("id") Integer id);

    @GetMapping("/pay/test")
    String getConsulTest();

    @GetMapping("/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    @GetMapping("/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);


    @GetMapping("/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    // 网关相关
    @GetMapping("/pay/gateway/info")
    ResultData<String> getGatewayInfo(@RequestParam(value = "userType",defaultValue = "") String userType);

}
