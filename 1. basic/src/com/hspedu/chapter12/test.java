package com.hspedu.chapter12;

public class test {
    public static void test() throws ArithmeticException {
        System.out.println(1 / 0);
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
