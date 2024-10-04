package com.atguigu.cloud.fallback;

import com.atguigu.cloud.apis.PayFeignSentinelApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author hf-dy
 * @date 2024/9/4 16:04
 */
@Component
public class PayFeignSentinelApiFallback implements PayFeignSentinelApi {
    @Override
    public ResultData<PayDTO> getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}