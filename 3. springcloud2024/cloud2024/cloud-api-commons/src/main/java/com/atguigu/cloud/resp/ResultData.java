package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hf-dy
 * @date 2024/8/31 16:05
 */

@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        var resultData = new ResultData<T>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setTimestamp(System.currentTimeMillis());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setTimestamp(System.currentTimeMillis());
        return resultData;
    }

}
