package com.hspedu.chapter08.hw03;

class AssociateProfessor extends Teacher {
    double salaryGrade = 1.2;
    String post = "副教授";

    public AssociateProfessor(String name, int age, double salary) {
        super(name, age, "副教授", salary);
    }

    @Override
    public void introduce() {
        super.introduce();
    }
}
