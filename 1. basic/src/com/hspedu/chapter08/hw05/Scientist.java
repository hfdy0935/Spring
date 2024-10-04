package com.hspedu.chapter08.hw05;

public class Scientist extends Employee {
    private final double bonusPerYear;

    public Scientist(String name, double salary, double bonusPerYear) {
        super(name, salary);
        this.bonusPerYear = bonusPerYear;
    }

    @Override
    public double getYearSalary() {
        return super.getYearSalary() + this.bonusPerYear;
    }
}
