package com.hfdy.sky.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 *
 * @param <T>
 */
@Data
public class ApiResult<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //错误信息
    private T data; //数据

    /**
     * 操作成功，没有数据
     *
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<T>();
        result.code = 1;
        return result;
    }

    /**
     * 操作成功，返回数据
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> success(T object) {
        ApiResult<T> result = new ApiResult<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    /**
     * 失败
     *
     * @param msg 失败原因
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> error(String msg) {
        ApiResult result = new ApiResult();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
