package com.hspedu.chapter13.hw01;

public class hw01 {
    public static String reverse(String str, int start, int end) {
        String str1 = str.substring(start, end + 1);
        StringBuffer sb = new StringBuffer(str1);
        sb.reverse();
        return str.substring(0, start) + sb + str.substring(end + 1);
    }

    public static void main(String[] args) {
        String str = "abcdef";
        System.out.println(reverse(str, 1, 4));
    }
}
