package com.hspedu.chapter08.hw05;

public class Employee {
    String name;
    double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double getYearSalary() {
        return this.baseSalary * 12;
    }
}
