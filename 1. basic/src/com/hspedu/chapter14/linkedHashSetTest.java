package com.hspedu.chapter14;

import java.util.LinkedHashSet;
import java.util.Objects;

public class linkedHashSetTest {
    public static void main(String[] args) {
        LinkedHashSet lst = new LinkedHashSet();
        lst.add(new Car("奥拓", 1000));
        lst.add(new Car("奥迪", 300000));//OK
        lst.add(new Car("法拉利", 10000000));//OK
        lst.add(new Car("奥迪", 300000));//加入不了
        lst.add(new Car("保时捷", 70000000));//OK
        lst.add(new Car("奥迪", 300000));//加入不了
        System.out.println("lst=" + lst);
    }
}

class Car {
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\nCar{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
