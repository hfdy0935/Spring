package com.hspedu.practise;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class t1 {


    @Test
    public void test() {
        Properties prop = new Properties();
        prop.setProperty("a", "1"); // 必须都是字符串
        System.out.println(prop.getProperty("a")); // 1，必须是字符串
        System.out.println(prop.get("a")); // 1，直接map.get()，没有约束

        // 读取配置文件
        try {
            prop.load(new FileInputStream("src/com/hspedu/practise/test.properties"));
            System.out.println(prop.get("username")); // 123
            System.out.println(prop.getProperty("password")); // 456
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

