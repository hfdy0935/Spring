package com.hspedu.chapter15.p5;

import java.util.ArrayList;
import java.util.TreeMap;

public class p5 {
    public static void main(String[] args) {
        ArrayList<Fish> fishArr = new ArrayList<>();
        Fish fish1 = new Fish("鱼1", 20, "河里");
        Fish fish2 = new Fish("鱼2", 50, "海里");
        fishArr.add(fish1);
        fishArr.add(fish2);

        ArrayList<Mouse> mouseArr = new ArrayList<>();
        Mouse mouse1 = new Mouse("鼠1", 5, "洞里");
        mouseArr.add(mouse1);

        ArrayList<Bird> birdArr = new ArrayList<>();
        Bird bird1 = new Bird("鸟1", 1, "巢里");
        Bird bird2 = new Bird("鸟2", 0.5, "天上");
        birdArr.add(bird1);
        birdArr.add(bird2);

        // 规定了类型的上界，只要是Food或Food的子类就行
        TreeMap<String, ArrayList<? extends Food>> foodMenu = new TreeMap<>();
        foodMenu.put("fish", fishArr);
        foodMenu.put("mouse", mouseArr);
        foodMenu.put("bird", birdArr);
        Cat<String, Food> cat = new Cat("小花", 2, "窝里", foodMenu);

        System.out.println(cat);
    }
}


class Food {
    private String name;
    private double weight;
    private String position;

    public Food(String name, double weight, String position) {
        this.name = name;
        this.weight = weight;
        this.position = position;
    }

    public String getCategory() {
        return name;
    }

    public void setCategory(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [name=" + name + ", weight=" + weight + ", position=" + position + "]";
    }
}


class Fish extends Food {
    public Fish(String name, double weight, String position) {
        super(name, weight, position);
    }
}

class Mouse extends Food {
    public Mouse(String name, double weight, String position) {
        super(name, weight, position);
    }
}

class Bird extends Food {
    public Bird(String name, double weight, String position) {
        super(name, weight, position);
    }
}


class Cat<K, V> {
    private String name;
    private int age;
    private String address;
    private TreeMap<K, V> foodMenu;

    public Cat(String name, int age, String address, TreeMap<K, V> foodMenu) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.foodMenu = foodMenu;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TreeMap<K, V> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(TreeMap<K, V> foodMenu) {
        this.foodMenu = foodMenu;
    }

    @Override
    public String toString() {
        return "Cat [name = " + name + ", age = " + age + ", address = " + address + ", foodMenu = " + foodMenu + "]";
    }
}