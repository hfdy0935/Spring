package com.hspedu.chapter13.hw03;

public class hw03 {
    public static void main(String[] args) {
        String name="Han Shun Ping";
        printName(name);
    }

    public static void printName(String str) {
        if (str == null) {
            System.out.println("输入不能为空");
            return;
        }
        String[] names = str.split(" ");
        if (names.length != 3) {
            System.out.println("输入字符串格式不对");
            return;
        }
        String res = names[2] + "," + names[0] + " ." + names[1].toUpperCase().charAt(0);
        System.out.println(res);
    }
}
