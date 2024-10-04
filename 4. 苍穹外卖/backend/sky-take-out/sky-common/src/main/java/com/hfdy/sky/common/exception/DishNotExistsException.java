package com.hfdy.sky.common.exception;

/**
 * @author hf-dy
 * @date 2024/9/30 14:05
 */

public class DishNotExistsException extends BaseException {
    public DishNotExistsException(String reason) {
        super(reason);
    }
}
