package com.hspedu.chapter08.hw01;

class Person {
    String name;
    int age;
    String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    @Override
    public String toString() {
        return "姓名：" + this.name + "\t" + "年龄：" + this.age + "\t" + "职业：" + this.job;
    }

}
