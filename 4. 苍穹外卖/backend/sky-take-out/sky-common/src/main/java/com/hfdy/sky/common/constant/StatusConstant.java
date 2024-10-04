package com.hfdy.sky.common.constant;

import java.util.Objects;

/**
 * 状态常量，启用或者禁用
 */
public class StatusConstant {

    //启用
    public static final Integer ENABLE = 1;

    //禁用
    public static final Integer DISABLE = 0;

    public static boolean containes(Integer status) {
        return Objects.equals(status, ENABLE) || Objects.equals(status, DISABLE);
    }
}
