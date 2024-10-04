package com.hspedu.chapter13;

public class StringBufferTest {
    public static void main(String[] args) {
        String str = "hello";
        // String 转 StringBuffer
        // 1.
        StringBuffer sb1 = new StringBuffer(str);
        // 2.
        StringBuffer sb2 = new StringBuffer();
        sb2.append(str);
        sb2.insert(0, str);
        System.out.println(sb1);
        System.out.println(sb2);
        // StringBuffer砖String
        // 1
        System.out.println(sb2.toString());
        // 2
        System.out.println(new String(sb2));

        // 常用方法
        StringBuffer sb3 = new StringBuffer("a");
        StringBuffer sb4 = sb3.append(" b").append(" c").insert(1, " g");
        System.out.println(sb4); // a g b c
        sb4.delete(1, 3);
        System.out.println(sb4); // a b c，左闭右开
        System.out.println(sb4.replace(0, 2, "")); // b c，返回了新StringBuffer，同时原字符串也被修改了
        System.out.println(sb4);// b c，左闭右开


        String price = "9128839238920.12938";
        StringBuffer sb5 = new StringBuffer(price);
        for (int i = sb5.lastIndexOf(".") - 3; i > 0; i -= 3) {
            sb5.insert(i, ".");
        }
        System.out.println("分隔后的价格：" + sb5); // 分隔后的价格：9.128.839.238.920.12938
    }
}
