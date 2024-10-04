package com.hspedu.chapter15.p3;

import java.util.ArrayList;
import java.util.Comparator;

public class p3 {
    public static void main(String[] args) {
        ArrayList<Employee> employeeArr = new ArrayList<>();
        Employee employee1 = new Employee("张三", 20, new MyDate(2000, 1, 20));
        Employee employee2 = new Employee("李四", 21, new MyDate(1999, 1, 20));
        Employee employee3 = new Employee("张三", 22, new MyDate(1998, 1, 20));
        employeeArr.add(employee1);
        employeeArr.add(employee2);
        employeeArr.add(employee3);
        // 遍历
        for (Employee employee : employeeArr) {
            System.out.println(employee);
        }
        // 排序
        employeeArr.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.equals(o2)) return 0;
                if (o1.getName().charAt(0) < o2.getName().charAt(0)) {
                    return -1;
                } else if (o1.getName().charAt(0) > o2.getName().charAt(0)) {
                    return 1;
                } else {
                    if (o1.getBirthday().lt(o2.getBirthday())) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
        System.out.println(employeeArr);
    }
}


class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    public boolean lt(MyDate other) {
        if (getYear() > other.getYear()) return false;
        else if (getMonth() > other.getMonth()) return false;
        else if (getDay() > other.getDay()) return false;
        else return true;
    }
}

class Employee {
    private String name;
    private double salary;
    private MyDate birthday;

    public Employee(String name, double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", salary=" + salary + ", birthday=" + birthday + "]";
    }
}