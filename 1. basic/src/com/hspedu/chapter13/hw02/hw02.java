package com.hspedu.chapter13.hw02;

import java.util.Scanner;
import java.util.regex.Pattern;

public class hw02 {
    public static void main(String[] args) {
        String username = "张三";
        String password = "123453";
        String email = "ado@aod.com";
        try {
            validateUser(username, password, email);
            System.out.println("注册成功");
        } catch (RuntimeException e) {
            System.out.println("注册失败，原因为：" + e.getMessage());
        }
    }

    public static void validateUser(String username, String password, String email) throws RuntimeException {
        if (username.length() < 2 || username.length() > 4) {
            throw new RuntimeException("用户名长度必须是2或3或4");
        }
        if (password.length() != 6 || !isDigit(password)) {
            throw new RuntimeException("要求密码长度为6，且都是数字");
        }
        int i = email.indexOf('@');
        int j = email.indexOf('.');
        if (!(i > 0 && j > i)) {
            throw new RuntimeException("邮箱必须包含@和.，且@在.前面");
        }
    }

    public static boolean isDigit(String str) {
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}

