package com.hspedu.java8Nnew;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferenceTest {
    @Test
    public void test() {
        // 1. 方法引用
        BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = Objects::equals;
        MyBiPredicate<String, String> bp3 = new MyBiPredicate<>();
        System.out.println(bp1.test("a", "a")); // true
        System.out.println(bp2.test("a", "b")); // false
        System.out.println(bp3.test("b", "b")); // true

        // 2. 构造器引用
        Supplier<Dog> fn1 = () -> new Dog();
        Supplier<Dog> fn2 = Dog::new;
        System.out.println(fn1.get() instanceof Dog); // true
        System.out.println(fn2.get() instanceof Dog); // true

        // 3. 数组引用
        Function<Integer, char[]> fn3 = (len) -> new char[len];
        Function<Integer, char[]> fn4 = char[]::new;
        System.out.println(fn3.apply(10));
        System.out.println(fn4.apply(10));
    }
}

class MyBiPredicate<T, V> {

    public boolean test(T s1, V s2) {
        return s1.equals(s2);
    }
}

class Dog {
    public Dog() {

    }
}