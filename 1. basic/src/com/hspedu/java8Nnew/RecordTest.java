package com.hspedu.java8Nnew;

public record RecordTest(int id, String name) {
    public static String info = "1";

    public static void method() {
    }

    public RecordTest() {
        this(0, null);
    }

    public void eat() {

    }
    // 不能再定义实例变量，初始化时已经声明
    // 类不能声明为abstract
    // 不能声明显式父类，有明确父类Record
    // final，不能有子类
}
