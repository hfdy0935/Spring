package com.hfdy.sky.common.exception;

/**
 * @author hf-dy
 * @date 2024/9/30 09:55
 */

public class DishNameDuplicatedException extends BaseException {
    public DishNameDuplicatedException(String reason) {
        super(reason);
    }
}
