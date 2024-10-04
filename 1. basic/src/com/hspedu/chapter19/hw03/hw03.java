package com.hspedu.chapter19.hw03;

import java.io.*;
import java.util.Properties;

@SuppressWarnings({"all"})
public class hw03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 初始化
        Properties prop = new Properties();
        String path = "D:\\桌面\\t\\Java\\javaPractise\\src\\com\\hspedu\\assets\\dog.properties";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        prop.load(new FileReader(path));
        prop.setProperty("name", "tom");
        prop.setProperty("age", "5");
        prop.setProperty("color", "red");
        prop.store(new FileOutputStream(path), null);

        // 读取
        Properties prop1 = new Properties();
        prop1.load(new FileReader(path));
        String name = prop1.getProperty("name");
        String age = prop1.getProperty("age");
        String color = prop1.getProperty("color");
        Dog dog = new Dog(name, Integer.parseInt(age), color);
        System.out.println(dog);
        System.out.println(dog.getType());

        // 序列化后保存到dog.dat中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(dog);

        // 读取试试
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Object obj = ois.readObject();
        if (obj instanceof Dog) {
            System.out.println(obj);
            System.out.println(((Dog) obj).getType());
        }
    }
}

class Dog implements Serializable {
    private String name;
    private int age;
    private String color;
    private final static String type = "dog";

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getType() {
        return type;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog [name=" + name + ", age=" + age + ", color=" + color + "]";
    }
}
