package com.hfdy.sky.common.exception;

/**
 * @author hf-dy
 * @date 2024/9/30 17:38
 */

public class StatusNotFoundException extends BaseException {
    public StatusNotFoundException(String reason) {
        super(reason);
    }
}
