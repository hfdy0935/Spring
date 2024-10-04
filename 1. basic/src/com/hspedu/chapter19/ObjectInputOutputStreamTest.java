package com.hspedu.chapter19;

import java.io.*;

public class ObjectInputOutputStreamTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\data.dat";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        // 保存，序列化
        oos.writeInt(100);
        oos.writeBoolean(true);
        oos.writeChar('a');
        oos.writeDouble(9.5);
        oos.writeUTF("字符串");
        oos.writeObject(new Dog("旺财", 10));
        oos.close();
        // 读取，反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());
        Dog dog = (Dog) ois.readObject(); // 读出来是Object，需要向下转型
        System.out.println(dog.name);
        ois.close();

    }
}


class Animal implements Serializable {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

// 让Dog能够序列化
class Dog extends Animal {
    private final static String type = "Dog";

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog [name=" + name + ", age=" + age + "]";
    }
}