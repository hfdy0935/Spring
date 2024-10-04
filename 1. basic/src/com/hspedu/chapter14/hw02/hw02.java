package com.hspedu.chapter14.hw02;

import java.util.ArrayList;
import java.util.Iterator;

public class hw02 {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList();
        Car car1 = new Car("本田", 10);
        Car car2 = new Car("本田", 100);
        cars.add(car1);
        cars.add(car2);
        cars.remove(car1);

        System.out.println(cars.contains(car2));
        System.out.println(cars.size());
        System.out.println(cars.isEmpty());

        ArrayList<Car> cars1 = new ArrayList();
        cars1.add(car2);
        System.out.println(cars.containsAll(cars1));
        cars.removeAll(cars1);
        System.out.println(cars);

        cars.add(car1);
        cars.add(car2);
        for (Car car : cars) {
            System.out.println(car);
        }
        Iterator<Car> carIterator = cars.iterator();
        while (carIterator.hasNext()) {
            System.out.println(carIterator.next());
        }
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
        return "Car [name=" + name + ", price=" + price + "]";
    }
}