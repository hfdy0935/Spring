package com.hspedu.chapter15.p4;


public class p4 {
    public static void main(String[] args) {
        Person<Double, String, Integer> p1 = new Person<>("张三", 20, 1000.5, "广东");
    }
}


class Person<D, S, I> {
    S name;
    I age;
    D salary;
    S address;


    public Person(S name, I age, D salary, S address) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.address = address;
    }
}

