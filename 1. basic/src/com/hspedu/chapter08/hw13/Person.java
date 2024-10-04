package com.hspedu.chapter08.hw13;

public class Person {
    private final String name;
    protected final int age;
    private final char gender;

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String play() {
        return this.name + "爱玩";
    }

    public void printInfo() {
        System.out.print("name：" + this.name + "\t" + "age：" + this.age + "\t" + "gender：" + this.gender + "\t");
    }
}
