package com.hfdy.sky.common.exception;

import lombok.NoArgsConstructor;

/**
 * @author hf-dy
 * @date 2024/9/28 22:36
 */
public class HasNoLoginPermissionException extends BaseException {
    public HasNoLoginPermissionException() {
    }

    public HasNoLoginPermissionException(String msg) {
        super(msg);
    }
}
