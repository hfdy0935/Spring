package com.hspedu.chapter10.p3;

public class p3 {
    public static void main(String[] args) {
        Child child = new Child();
    }
}

class Father {
    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类普通代码块");
    }

    public Father() {
        System.out.println("父类构造函数");
    }
}

class Child extends Father {
    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类普通代码块");
    }

    public Child() {
        System.out.println("子类构造函数");
    }

}