package com.hspedu.chapter08.hw01;

/**
 * 创建三个Person，并对age进行冒泡排序
 */
public class hw01 {
    public static void main(String[] args) {
        Person p1 = new Person("张三", 20, "成像仪");
        Person p2 = new Person("李四", 21, "程序员");
        Person p3 = new Person("王五", 18, "促销员");
        Person[] personArr = {p1, p2, p3};
        for (int i = 0; i < personArr.length; i++) {
            for (int j = 0; j < personArr.length - 1 - i; j++) {
                if (personArr[j].age > personArr[j + 1].age) {
                    Person temp = personArr[j];
                    personArr[j] = personArr[j + 1];
                    personArr[j + 1] = temp;
                }
            }
        }
        for (Person p : personArr) {
            System.out.println(p.toString());
        }
    }
}

