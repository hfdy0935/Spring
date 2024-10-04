package com.hfdy.sky.common.exception;

/**
 * @author hf-dy
 * @date 2024/9/30 17:44
 */

public class DeletionFailException extends BaseException {
    public DeletionFailException(String reason) {
        super(reason);
    }
}
