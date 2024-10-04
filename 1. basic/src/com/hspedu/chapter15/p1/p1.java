package com.hspedu.chapter15.p1;

import java.util.ArrayList;

public class p1 {
    // 函数泛型传参写法
    public static <T> T returnSameType(T args) {
        return args;
    }

    public static void main(String[] args) {
        ArrayList<Animal<Integer>> animalArr = new ArrayList<>();
        Dog<Integer> dog = new Dog<>(0);
        Cat<Integer> cat = new Cat<>(1);
        Animal<Integer> animal = new Animal<>(2);
        animalArr.add(dog);
        animalArr.add(cat);
        animalArr.add(animal);

        Integer args_ = cat.getArgs();
        System.out.println(args_); // 1
        //
        String res1 = p1.returnSameType("abc");
        System.out.println(res1); // abc
        ArrayList<Animal<Integer>> res2 = p1.returnSameType(animalArr);
        System.out.println(res2); // [Dog, Cat, Animal]
    }
}

interface IAnimal<T> {
    T getArgs();
}

// 类泛型传参写法
class Animal<T> implements IAnimal<T> {
    private final T args;

    public Animal(T args) {
        this.args = args;
    }

    public T getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Cat<T> extends Animal<T> {

    public Cat(T args) {
        super(args);
    }
}

class Dog<T> extends Animal<T> {
    public Dog(T args) {
        super(args);
    }
}