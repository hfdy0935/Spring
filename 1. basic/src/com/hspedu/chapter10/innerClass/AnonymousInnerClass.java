package com.hspedu.chapter10.innerClass;

public class AnonymousInnerClass {
    public static void main(String[] args) {
        Outer1 outer1 = new Outer1();
        outer1.f1();
        outer1.f2();
    }
}

class Outer1 {
    private int n1 = 10;

    public void f1() {
        // 匿名内部类
        Person p = new Person() {
            private int n1 = 90;

            @Override
            public void hi() {
                System.out.println("匿名内部类重写的hi()");
                System.out.println(Outer1.this.n1);
                System.out.println(n1);
            }
        };
        p.hi();
    }

    public void f2() {
        // 局部内部类
        class PersonCopy extends Person {
            private int n1 = 90;

            @Override
            public void hi() {
                System.out.println("非匿名类内部重写的hi()");
                System.out.println(Outer1.this.n1);
                System.out.println(n1);
            }
        }
        PersonCopy p = new PersonCopy();
        p.hi();
    }


}

class Person {
    public void hi() {
        System.out.println("person hi()");
    }

    public void ok(String str) {
        System.out.println("Person ok() " + str);
    }
}