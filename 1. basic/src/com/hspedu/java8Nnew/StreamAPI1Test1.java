package com.hspedu.java8Nnew;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI1Test1 {
    @Test
    public void test1() {
        // 1. 通过集合
        var arr = new ArrayList<String>();
        arr.add("1");
        arr.add("2");
        // 可选的
        Function<String, String> fn1 = (s) -> s + 2;
        Optional<String> obj = arr.stream().map(fn1).reduce((pre, curr) -> pre + curr);
        System.out.println(obj.get()); // 获取值，1222

        // 2. 通过数组
        var intArr1 = new Integer[]{1, 2, 3};
        Arrays.stream(intArr1).filter(i -> i >= 2).forEach(System.out::println); // 2 3

        // 3. Stream.of()
        Integer[] intArr2 = Arrays.copyOf(intArr1, intArr1.length);
        Stream.of(intArr2).filter(i -> i >= 2).forEach(System.out::println); // 2 3

        // 4. 创建无限流
        // 迭代
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 2);
        stream1.limit(10).forEach(System.out::println);
        // 生成
        Stream<Double> stream2 = Stream.generate(Math::random);
        stream2.limit(10).forEach(System.out::println);
    }

    @Test
    public void test2() {
        var intArr1 = new Integer[]{1, 2, 3};
        Stream<Integer> obj1 = Arrays.stream(intArr1).map(i -> i * 2); // 中间结果
        System.out.println(obj1); // java.util.stream.ReferencePipeline$3@5a01ccaa
        List<Integer> res1 = obj1.collect(Collectors.toList());
//        List<Integer> res2 = obj1.toList(); // 流只能用一次
    }
}
