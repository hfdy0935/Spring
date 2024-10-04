package com.atguigu.cloud.resp;

import lombok.Getter;

/**
 * @author hf-dy
 * @date 2024/8/31 15:53
 */

@Getter
public enum ReturnCodeEnum {
    RC999("999", "操作xxx失败"),
    RC200("200", "success"),
    RC301("301", "永久重定向"),
    RC401("401", "Authorization error"),
    RC403("403", "Forbidden"),
    RC404("404", "Not found"),
    RC405("405", "Method not allowed"),
    RC500("500", "Server error");


    private final String code;
    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
