package com.hspedu.chapter10.abstractPractise;

public class AbstractPractise1 {
    public static void main(String[] args) {
        XiaoMing xiaoMing = new XiaoMing();
    }
}


abstract class PersonModel {
    public static String[] hobbies;

    static {
        System.out.println("静态块");
    }

    public void print() {
        System.out.println("打印");
    }

    abstract String getName();

    abstract int getAge();
}


class XiaoMing extends PersonModel {
    public String getName() {
        return "小明";
    }

    public int getAge() {
        return 20;
    }

}
