package com.hspedu.chapter08.hw05;

public class Teacher extends Employee {
    private int classDay;
    private double classSal;

    public Teacher(String name, double salary, int classDay, double classSal) {
        super(name, salary);
        this.classDay = classDay;
        this.classSal = classSal;
    }

    @Override
    public double getYearSalary() {
        return super.getYearSalary() + this.classDay * this.classSal;
    }

}
