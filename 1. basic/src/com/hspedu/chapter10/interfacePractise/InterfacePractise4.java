package com.hspedu.chapter10.interfacePractise;

public class InterfacePractise4 {
    public static void main(String[] args) {
        IAnimal cat = new Cat();
        IAnimal dog = new Dog();
//        多态数组
        IAnimal[] animalArr = {cat, dog};
        for (IAnimal a : animalArr) {
//            需要向下转型才能确定具体是什么类
            if (a instanceof Cat) {
                ((Cat) a).catRun();
            } else if (a instanceof Dog) {
                ((Dog) a).dogRun();
            }
        }

        IAnimal cat1 = new Cat();
        cat1 = new ChinaCat();

        IAnimal a1 = new Cat();
        ICat a2 = new Cat();
    }
}


interface IAnimal {

    void cry();
}

interface ICat extends IAnimal {

    void catRun();
}

interface IDog extends IAnimal {
    void dogRun();
}

class Cat implements ICat {
    public void cry() {
        System.out.println("猫叫");
    }

    public void catRun() {
        System.out.println("猫跑");
    }

    @Override
    public String toString() {
        return "猫";
    }
}

class Dog implements IDog {
    public void cry() {
        System.out.println("狗吠");
    }

    public void dogRun() {
        System.out.println("狗跑");
    }

    @Override
    public String toString() {
        return "狗";
    }
}

class ChinaCat extends Cat {
}