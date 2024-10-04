package com.hspedu.chapter08.hw04;

class Manager extends Employee {
    double grade = 1.2;

    public Manager(String name, double salaryPerDay, int workDayNum) {
        super(name, salaryPerDay, workDayNum);
    }

    @Override
    public double printSalary() {
        return super.printSalary() * this.grade;
    }
}
