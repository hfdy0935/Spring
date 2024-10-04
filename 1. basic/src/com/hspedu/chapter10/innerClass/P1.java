package com.hspedu.chapter10.innerClass;

public class P1 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        System.out.println(outer.getInnerInstance());
    }
}

class Outer {
    private int n1 = 100;

    class Inner {
        public int n1 = 200;

        void printN1() {
            System.out.println(this.n1);
            System.out.println(n1);
            System.out.println(Outer.this.n1);
        }

        @Override
        public String toString() {
            return "Inner类";
        }
    }

    public Inner getInnerInstance() {
        return new Inner();
    }
}