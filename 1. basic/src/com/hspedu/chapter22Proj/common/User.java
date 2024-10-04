package com.hspedu.chapter22Proj.common;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String password;

    public User() {
    }

    public User(String userId,String password) {
        this.password = password;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
