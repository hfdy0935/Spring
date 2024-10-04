package com.hspedu.java8Nnew;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaTest {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };
        new Thread(r1).start();

        Runnable r2 = () -> System.out.println("hello java");
        new Thread(r2).start();
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        System.out.println(com1.compare(1, 2));

        Comparator<Integer> com2 = (o1, o2) -> o1 - o2;
        System.out.println(com2.compare(1, 2));
    }

    @Test
    public void test3() {
        Consumer<Integer> con1 = new Consumer<>() {
            @Override
            public void accept(Integer t) {
                System.out.println(t);
            }
        };
        con1.accept(1); // 1

        // 类型推断
        Consumer<Integer> con2 = t -> System.out.println(t);
        con2.accept(2);
        Consumer<String> con3 = (String t) -> System.out.println(t);
        con3.accept("hello");

        // 方法引用
        Consumer<String> con4 = System.out::println;
        con4.accept("hello");
        var cat = new Cat();
        Consumer con5 = cat::cry; // 把函数看成变量传来传去
        con5.accept("1");
        Consumer con6 = Cat::getOriginalInput;
        con6.accept(123);
    }
}

class Cat {
    public static <T> T getOriginalInput(T t) {
        return t;
    }

    public <T> void cry(T s) {
        System.out.println("猫叫" + s);
    }
}
