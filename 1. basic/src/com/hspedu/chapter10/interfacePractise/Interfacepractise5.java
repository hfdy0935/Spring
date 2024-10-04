package com.hspedu.chapter10.interfacePractise;

public class Interfacepractise5 {
}

interface A {
    int x = 0;
}

class B {
    int x = 1;
    public static int y = 1;
}

class C extends B implements A {
    public void pX() {
        System.out.println(A.x); // 0
        System.out.println(super.x); // 1
    }
}