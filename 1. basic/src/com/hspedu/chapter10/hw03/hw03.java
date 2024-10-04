package com.hspedu.chapter10.hw03;

public class hw03 {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        cat.shout();
        dog.shout();
        //
        System.out.println("-------------------------------------------");
        Animal cat2 = new Animal() {
            @Override
            public void shout() {
                System.out.println("猫会喵喵叫");
            }
        };
        cat2.shout();
        (new Animal() {
            @Override
            public void shout() {
                System.out.println("狗会汪汪叫");
            }
        }).shout();
    }

}

abstract class Animal {
    abstract void shout();
}

class Cat extends Animal {
    @Override
    public void shout() {
        System.out.println("猫会喵喵叫");
    }
}

class Dog extends Animal {
    @Override
    public void shout() {
        System.out.println("狗会汪汪叫");
    }
}