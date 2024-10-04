package com.atguigu.cloud.exp;

import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hf-dy
 * @date 2024/8/31 16:30
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public ResultData<String> exception(Exception e) {
//        System.out.println("come in GlobalExceptionHandler");
//        log.error("全局异常信息：" + e.getMessage());
//        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
//    }
}
