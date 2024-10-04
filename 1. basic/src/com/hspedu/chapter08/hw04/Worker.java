package com.hspedu.chapter08.hw04;

class Worker extends Employee {
    double grade = 1.0;

    public Worker(String name, double salaryPerDay, int workDayNum) {
        super(name, salaryPerDay, workDayNum);
    }

    @Override
    public double printSalary() {
        return super.printSalary() * this.grade;
    }
}