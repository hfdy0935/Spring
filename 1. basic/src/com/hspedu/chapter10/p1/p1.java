package com.hspedu.chapter10.p1;

public class p1 {
    public static void main(String[] args) {
        System.out.println(A.name);
        System.out.println(A.getStaticName());
    }

}

class A {
    public static final String name = "A类";

    protected static String getStaticName() {
        return A.name;
    }
}
