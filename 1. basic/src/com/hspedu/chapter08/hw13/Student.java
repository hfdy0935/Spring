package com.hspedu.chapter08.hw13;

public class Student extends Person {
    private final String stu_id;

    public Student(String name, int age, char gender, String stu_id) {
        super(name, age, gender);
        this.stu_id = stu_id;
    }

    public void study() {
        System.out.println("我承诺，我会好好学习。");
    }

    @Override
    public String play() {
        return super.play() + "足球";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("stu_id：" + this.stu_id);
    }
}
