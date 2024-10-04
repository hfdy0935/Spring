package com.hspedu.chapter13;

public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("abc");
        System.out.println(sb1);
        String i = String.valueOf('a');
        System.out.println('a' + "");
        sb1.append(10);
    }
}
