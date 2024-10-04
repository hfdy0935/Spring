package com.hspedu.chapter13;

public class Test01 {
    public static void main(String[] args) {
        // Integer
        int n1 = 10;
        // 手动装箱，int => Integer
        Integer integer1 = new Integer(n1);
        Integer integer2 = Integer.valueOf(n1);
        // 手动拆箱， Inteegr => int
        int n2 = integer2.intValue();

        // 自动装箱
        Integer integer3 = n1;
        // 自动拆箱
        int n3 = integer3;

        // String
        String s1 = n1 + "";
        String s2 = integer3.toString();
        String s3 = String.valueOf(n2);
        n1 = Integer.parseInt(s1);
        n1 = new Integer(s1);


        // Integer常用方法
        System.out.println(Integer.MIN_VALUE); // 0x80000000
        System.out.println(Integer.MAX_VALUE); // 0x7fffffff
        // 还有很多
        // Character常用方法
        System.out.println(Character.isDigit('a')); // 是不是数字
        System.out.println(Character.isLetter('1')); // 是不是字母
        System.out.println(Character.isUpperCase('A'));
        System.out.println(Character.isLetter('a'));
        System.out.println(Character.isWhitespace(' ')); // 是不是空格
        System.out.println(Character.toUpperCase('a'));
        System.out.println(Character.toLowerCase('A'));
    }
}

