package com.atguigu.schedule.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 传入美剧结果的构造函数
     *
     * @param resultCodeEnum ResultCodeEnum
     * @param data           T
     */
    public Result(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.data = data;
    }
}
