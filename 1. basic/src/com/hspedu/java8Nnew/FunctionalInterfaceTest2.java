package com.hspedu.java8Nnew;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class FunctionalInterfaceTest2 {
    private static final ArrayList<String> strArr = new ArrayList<>();

    static {
        strArr.add("1");
        strArr.add("2");
        strArr.add("3");
    }

    public <K, V> HashMap<K, V> getHashMap(MyEntry<K, V> args) {
        var map = new HashMap<K, V>();
        map.put(args.getKey(), args.getValue());
        return map;
    }

    @Test
    public void test2() {
        // 1. 消费型接口
        Consumer<ArrayList<String>> con1 = (arg) -> {
            System.out.println("加元素前：" + arg);
            arg.add("999");
            System.out.println("加元素后：" + arg);
        };
        con1.accept(strArr);

        // 2. 供给型接口
        Supplier<Double> sup1 = () -> Math.random();
        Supplier<Double> sup2 = Math::random;
        System.out.println(sup1.get());
        System.out.println(sup2.get());

        // 3. 函数型接口
        Function<String, char[]> fn1 = args -> args.toCharArray();
        char[] res = fn1.apply("abc"); // abc
        System.out.println(res);
        Function<MyEntry<String, Integer>, HashMap<String, Integer>> fn2 = new FunctionalInterfaceTest2()::getHashMap;
        System.out.println(fn2.apply(new MyEntry<>("aaa", 1))); // {aaa=1}

        // 4. 断定型接口
        Predicate<Integer> fn4 = arg -> {
            // 复杂逻辑
            return arg + 1 > 0;
        };
        System.out.println(fn4.test(-1)); // false

    }
}

class MyEntry<K, V> {
    private K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}