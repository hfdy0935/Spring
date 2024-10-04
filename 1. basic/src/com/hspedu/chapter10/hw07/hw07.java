package com.hspedu.chapter10.hw07;

public class hw07 {
    public static void main(String[] args) {
        Car c1 = new Car(100);
        Car c2 = new Car(30);
        Car c3 = new Car(-10);
        c1.new Air().flow();
        c2.new Air().flow();
        c3.new Air().flow();
    }
}

class Car {
    private final double temperature;

    public Car(double temperature) {
        this.temperature = temperature;
    }

    class Air {
        public void flow() {
            if (Car.this.temperature > 40) {
                System.out.println("吹冷气");
            } else if (Car.this.temperature >= 0) {
                System.out.println("关闭中...");
            } else {
                System.out.println("吹暖气");
            }
        }
    }
}