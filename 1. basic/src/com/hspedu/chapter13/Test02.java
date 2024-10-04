package com.hspedu.chapter13;

public class Test02 {
    public static void compare(Integer n1, Integer n2) {
        boolean[] res = new boolean[2];
        System.out.print(n1.equals(n2) + " ");
        System.out.println(n1 == n2);
    }

    public static void main(String[] args) {
        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);
        Integer i3 = 10;
        Integer i4 = 10;
        Integer i5 = 128;
        Integer i6 = 128;
        int i7 = 10;
        int i8 = 10;
        int i9 = new Integer(10);
        int i10 = new Integer(10);
        compare(i1, i2); // true false
        compare(i3, i4); // true true
        compare(i5, i6); // true falses
        compare(i7, i8); // true true
        compare(i9, i10); // true true
        compare(i1, i9); // true false
    }
}
