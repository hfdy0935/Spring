package com.hspedu.chapter08.hw04;

class Employee {
    String name;
    double salaryPerDay;
    int workDayNum;

    public Employee(String name, double salaryPerDay, int workDayNum) {
        this.name = name;
        this.salaryPerDay = salaryPerDay;
        this.workDayNum = workDayNum;
    }

    public double printSalary() {
        return this.salaryPerDay * this.workDayNum;
    }
}