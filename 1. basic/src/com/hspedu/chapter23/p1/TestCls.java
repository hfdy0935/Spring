package com.hspedu.chapter23.p1;

public class TestCls {
    public double n1;
    public double n2;

    public static String name = "xxx";
    public double num = 1.1;

    private int privateInt = 1;

    public TestCls() {
    }

    public TestCls(double n1, double n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public static void func1() {
        System.out.println("func1()");
    }

    public static void func1(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String str : args) {
            sb.append(str);
        }
        System.out.println("func1(" + sb + ")");
    }

    public double add(double anotherDouble) {
        return n1 + n2 + anotherDouble;
    }
}
