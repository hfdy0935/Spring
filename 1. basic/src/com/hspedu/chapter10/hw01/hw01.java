package com.hspedu.chapter10.hw01;

public class hw01 {
    public static void main(String[] args) {
        Car c1 = new Car();
        Car c2 = new Car(100);
        System.out.println(c1);
        System.out.println(c2);
    }
}

class Car {
    double price = 10;
    static String color = "white";

    public String toString() {
        return price + '\t' + color;
    }

    public Car() {
        this.price = 9;
        this.color = "red"; // 通过实例引用访问静态属性
    }

    public Car(double price) {
        this.price = price;
    }
}