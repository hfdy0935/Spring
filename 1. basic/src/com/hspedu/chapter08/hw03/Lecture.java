package com.hspedu.chapter08.hw03;

class Lecture extends Teacher {
    double salaryGrade = 1.1;
    String post = "讲师";

    public Lecture(String name, int age, double salary) {
        super(name, age, "讲师", salary);
    }

    @Override
    public void introduce() {
        super.introduce();
    }
}
