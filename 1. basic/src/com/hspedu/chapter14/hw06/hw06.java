package com.hspedu.chapter14.hw06;

import java.util.HashSet;

public class hw06 {
    public static void main(String[] args) {
        HashSet<Person> hs = new HashSet<>();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        hs.add(p1);
        hs.add(p2);
        p1.setName("CC");
        System.out.println(hs);

        hs.remove(p1);
        System.out.println(hs);

        hs.add(new Person(1001, "CC"));
        System.out.println(hs);

        hs.add(new Person(1001, "AA"));
        System.out.println(hs);
    }
}

class Person {
    private String name;
    private int age;

    public Person(int age, String name) {
        this.name = name;
        this.age = age;
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
        return "Person [name=" + name + ", age=" + age + "]";
    }
}