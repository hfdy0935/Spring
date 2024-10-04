package com.hspedu.chapter10.hw06;

public class hw06 {
    public static void main(String[] args) {
        Person person = new Person("唐僧", VehiclesFactory.getHorse());
        person.common();
        person.passRiver();
        person.common();
        person.passRiver();
    }
}

interface Vehicles {
    void work();
}

class Horse implements Vehicles {
    private static int num = 0;

    public Horse() {
        num++;
    }

    @Override
    public void work() {
        System.out.println("骑马，第" + num + "匹");
    }
}

class Boat implements Vehicles {
    private static int num = 0;

    public Boat() {
        num++;
    }

    @Override
    public void work() {
        System.out.println("坐船，第" + num + "只");
    }
}

class VehiclesFactory {
    private static final Horse horse = new Horse();

    public static Horse getHorse() {
        horse.work();
        return horse;
    }

    public static Boat getBoat() {
        Boat boat = new Boat();
        boat.work();
        return boat;
    }
}

class Person {
    public String name;
    public Vehicles vehicles;

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public void common() {
        vehicles = VehiclesFactory.getHorse();
    }

    public void passRiver() {
        vehicles = VehiclesFactory.getBoat();
    }
}