package com.hspedu.chapter10.SingleTon;

public class SingleTon {
    public static void main(String[] args) {
        Person p1 = Person.getInstance();
        Person p2 = Person.getInstance();
        System.out.println(p1);
        System.out.println(p2);

        Animal animal1 = Animal.getInstance("cat");
        Animal animal2 = Animal.getInstance("dog");
        System.out.println(animal1);
        System.out.println(animal1);

    }
}

class Person {
    public static Person p = new Person("张三");

    public static Person getInstance() {
        return p;
    }

    private String name;

    private Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name：" + this.name;
    }
}

class Animal {
    private static Animal animal;
    private String name;

    private Animal(String name) {
        this.name = name;
    }

    public static Animal getInstance(String name) {
        if (Animal.animal == null) {
            Animal.animal = new Animal(name);
        }
        return animal;
    }

    @Override
    public String toString() {
        return "name：" + this.name;
    }
}
