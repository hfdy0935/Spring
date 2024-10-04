package com.hfdy.sky.common.utils;

import com.hfdy.sky.common.exception.BaseException;

/**
 * @author hf-dy
 * @date 2024/10/2 10:05
 */

public class ExceptionUtil {
    public static void throwIf(boolean condition, BaseException exception) {
        if (condition) throw exception;
    }

    public static void throwIf(boolean condition, String msg) {
        throwIf(condition, new BaseException(msg));
    }

    public static void throwIf(boolean condition) {
        throwIf(condition, "");
    }
}
