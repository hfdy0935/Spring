package com.hspedu.chapter14.hw05;

import java.util.TreeSet;

public class hw05 {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        // 会报错
        // Person cannot be cast to java.lang.Comparable
        // 必须实现Comparable方法
        ts.add(new Person());
    }
}

class Person {

}