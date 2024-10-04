package com.hspedu.chapter14;

import java.time.LocalDate;
import java.util.*;

public class ArrayListTest1 {
    public static void main(String[] args) {
        ArrayList al1 = new ArrayList();
        al1.add(1);
        al1.add("abs");
        al1.add(LocalDate.now());
        al1.add(new Animal("张三", 20));
        al1.remove(1); // 删掉了索引为1的元素，"abs"
        // 迭代器
        Iterator iterator = al1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // for循环增强
        for (Object o : al1) {
            System.out.println(o);
        }
        // 按下标遍历
        for (int i = 0; i < al1.size(); i++) {
            System.out.println(al1.get(i));
        }

        // List
        List l = new ArrayList();
        for (int i = 0; i < 100; i++) {
            l.add(i);
        }
        l.add(1, "韩顺平教育");
        System.out.println(l.get(4));
        l.remove(5);
        l.set(6, "修改后的值");

    }
}

class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
