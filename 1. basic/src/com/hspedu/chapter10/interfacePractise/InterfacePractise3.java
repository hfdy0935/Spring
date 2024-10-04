package com.hspedu.chapter10.interfacePractise;

public class InterfacePractise3 {
    public static void main(String[] args) {
        LittleMonkey wuKong = new LittleMonkey("悟空");
        wuKong.climbing();
        wuKong.swimming();
        wuKong.flying();
    }
}

interface Fishable {
    void swimming();
}

interface Birdable {
    void flying();
}

class Monkey {
    private String name;

    public Monkey(String name) {
        this.name = name;
    }

    public void climbing() {
        System.out.println(name + "会爬树...");
    }

    public String getName() {
        return name;
    }
}

class LittleMonkey extends Monkey implements Fishable, Birdable {
    public LittleMonkey(String name) {
        super(name);
    }

    @Override
    public void swimming() {
        System.out.println(getName() + "游泳...");
    }

    @Override
    public void flying() {
        System.out.println(getName() + "飞...");
    }
}