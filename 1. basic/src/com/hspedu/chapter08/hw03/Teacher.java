package com.hspedu.chapter08.hw03;

class Teacher {
    String name;
    int age;
    String post;
    double salary;

    public Teacher(String name, int age, String post, double salary) {
        this.name = name;
        this.age = age;
        this.post = post;
        this.salary = salary;
    }

    public void introduce() {
        System.out.println("姓名：" + this.name + "\t年龄：" + this.age + "\t职称：" + this.post + "\t薪资：" + this.salary);
    }
}