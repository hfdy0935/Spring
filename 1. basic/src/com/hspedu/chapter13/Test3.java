package com.hspedu.chapter13;

public class Test3 {
    String str = new String("hsp");
    final char[] ch = {'j', 'a', 'v', 'a'};

    public void change(String str, char[] ch) {
        str = "java";
        ch[0] = 'h';
    }

    public static void main(String[] args) {
        Test3 t = new Test3();
        t.change(t.str, t.ch);
        System.out.println(t.str); // 基本类型，改了形参不影响原变量
        System.out.println(t.ch); // 引用类型，会影响
    }
}

