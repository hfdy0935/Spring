package com.hspedu.java8Nnew;

import org.junit.Test;


public class FunctionalInterfaceTest1 {

    @Test
    public void test1() {
        MyNumber<Double> mn1 = new MyNumber<>() {
            @Override
            public Double getValue(Double arg) {
                return arg + 1.0;
            }
        };
        System.out.println(mn1.getValue(2.0));

        MyNumber<Integer> mn2 = arg -> arg + 1;
        System.out.println(mn2.getValue(2));
    }


}

@FunctionalInterface
interface MyNumber<T> {
    T getValue(T args);
}

