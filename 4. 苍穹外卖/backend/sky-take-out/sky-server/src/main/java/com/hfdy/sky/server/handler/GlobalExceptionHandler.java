package com.hfdy.sky.server.handler;

import com.hfdy.sky.common.exception.BaseException;
import com.hfdy.sky.common.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ApiResult<String> exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return ApiResult.error(ex.getMessage());
    }
}
