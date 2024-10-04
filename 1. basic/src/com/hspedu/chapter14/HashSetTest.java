package com.hspedu.chapter14;

import java.util.HashSet;
import java.util.Objects;

public class HashSetTest {
    public static void main(String[] args) {
        HashSet employeeSet = new HashSet();


    }
}

class Employee {
    private String name;
    private int age;

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.getAge() && Objects.equals(name, employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}