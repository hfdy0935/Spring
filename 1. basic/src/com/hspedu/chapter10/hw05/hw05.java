package com.hspedu.chapter10.hw05;

public class hw05 {
    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        b.show();
    }
}

class A {
    private String name = "A中的name";

    class B {
        private final String name = "B中的name";

        public void show() {
            System.out.println(name);
            System.out.println(A.this.name);
        }
    }
}