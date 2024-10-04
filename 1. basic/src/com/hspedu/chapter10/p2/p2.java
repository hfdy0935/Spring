package com.hspedu.chapter10.p2;

public class p2 {
    public static void main(String[] args) {
        Person person1 = new Person("张三");
        Person person2 = new Person("张三");
//        static 代码块1
//        static 代码块2
//        normal 代码块2
//        初始化完成
//        normal 代码块2
//        初始化完成
    }
}

class Person {
    public static String str = "1";
    private String name;

    static {
        System.out.println("static 代码块1");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("初始化完成");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    {
        System.out.println("normal 代码块2");
    }

    static {
        System.out.println("static 代码块2");
    }
}