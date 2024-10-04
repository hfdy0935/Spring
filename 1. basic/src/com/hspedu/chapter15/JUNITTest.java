package com.hspedu.chapter15;


import org.junit.Test;

public class JUNITTest {
    private static String str = "测试";

    public static void main(String[] args) {

    }

    public void print() {
        System.out.println("打印");
    }

    @Test
    public void m1() {
        System.out.println(str);
        print();
        System.out.println("执行m1");
    }
}
