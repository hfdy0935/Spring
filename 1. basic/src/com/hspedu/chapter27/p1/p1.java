package com.hspedu.chapter27.p1;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p1 {
    public static void main(String[] args) {
        Pattern p1 = Pattern.compile("(\\d)(\\d)\\2\\1");
        Matcher m1 = p1.matcher("1221");
        while (m1.find()) {
            System.out.println(m1.group(0)); // 1221
            System.out.println(m1.group(1)); // 1
            System.out.println(m1.group(2)); // 2
        }
        System.out.println(Pattern.matches("(\\d)(\\d)\\2\\1", "1221")); // true
    }

    @Test
    public void test() {
        Pattern p1 = Pattern.compile("www\\.(.*?)\\.com");
        String str = "https://www.bilibili.com, https://www.baidu.com";
        Matcher m1 = p1.matcher(str);
        while (m1.find()) {
            System.out.println(m1.group(1)); // bilibili baidu
        }
    }

    @Test
    public void test1() {
        Pattern p1 = Pattern.compile("(.)\\1+");
        Matcher m1 = p1.matcher("我要学学学学JavaScript");
        String res = m1.replaceAll("$1"); // 把所有匹配到的 "学" 换成一个 "学"
        System.out.println(res); // 我要学JavaScript

    }

}
