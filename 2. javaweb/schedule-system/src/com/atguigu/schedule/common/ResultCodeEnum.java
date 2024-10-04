package com.atguigu.schedule.common;

public enum ResultCodeEnum {
    SUCCESS(200, "success"),
    FAIL(500, "fail"),
    NOT_LOGIN(504, "notLogin");


    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
