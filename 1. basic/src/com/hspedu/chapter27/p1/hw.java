package com.hspedu.chapter27.p1;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class hw {
    @Test
    public void test1() {
        String emailStr = "111@a.b.c";
        System.out.println(emailStr.matches("^[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$"));
    }

    @Test
    public void test2() {
        String number1 = "+0";
        if (number1.matches("^[-+]?(0|[1-9]\\d*)(\\.(0|\\d*[1-9]))?$")) {
            System.out.println("是整数或小数");
        }
    }

    @Test
    public void test3() {
        String url = "http://www.sohu.com:8000/abc/index.htm";
        // 文件名前面用贪婪匹配，确保文件名能匹配到最后面的
        Pattern pattern = Pattern.compile("^(?<protocol>.*?)://(?<domain>.*?):(?<port>\\d+).*/(?<filename>.*?)$");
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            System.out.println("协议：" + matcher.group("protocol"));
            System.out.println("域名：" + matcher.group("domain"));
            System.out.println("端口：" + matcher.group("port"));
            System.out.println("文件名：" + matcher.group("filename"));
        }

    }
}
