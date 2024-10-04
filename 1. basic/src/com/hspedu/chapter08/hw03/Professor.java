package com.hspedu.chapter08.hw03;

class Professor extends Teacher {
    double salaryGrade = 1.3;
    String post = "教授";

    public Professor(String name, int age, double salary) {
        super(name, age, "教授", salary);
    }

    @Override
    public void introduce() {
        super.introduce();
    }
}
