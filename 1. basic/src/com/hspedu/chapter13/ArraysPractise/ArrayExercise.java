package com.hspedu.chapter13.ArraysPractise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArrayExercise {
    public static void main(String[] args) {
        Integer[] integers = {1, 20, 90, 300, 10, 1000, 40, 2};
        // 1. 转为字符串
        String str = Arrays.toString(integers); // [1, 20, 90, 300, 10, 1000, 40, 2]
        StringBuffer stringBuffer = new StringBuffer(str);
        System.out.println(stringBuffer.append(111)); // [1, 20, 90, 300, 10, 1000, 40, 2]111
        // 2. 排序
        Arrays.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer i1 = (Integer) o1;
                Integer i2 = (Integer) o2;
                return i1 - i2;
            }
        });
        // 排好序的 1 2 10 20 40 90 300 1000 ，类似于js数组的sort，传入函数，返回负数顺序不变，返回正数调换顺序，返回0则不管大小都不调换顺序
        for (Integer i : integers) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 冒泡排序
        Integer[] integers1 = {1, 20, 90, 300, 10, 1000, 40, 2};
        for (int i = 0; i < integers1.length - 1; i++) {
            for (int j = 0; j < integers1.length - 1 - i; j++) {
                if (integers1[j] > integers1[j + 1]) {
                    Integer temp = integers1[j];
                    integers1[j] = integers1[j + 1];
                    integers1[j + 1] = temp;
                }
            }
        }
        // 1 2 10 20 40 90 300 1000
        for (Integer i : integers1) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 3. 二分法查找，参数必须排好序
        int index = Arrays.binarySearch(integers1, 40);
        System.out.println(index); // 4

        // 4. 复制
        System.out.println(Arrays.copyOf(integers1, 1));

        // 填充
        Arrays.fill(integers1, 100);
        // 100 100 100 100 100 100 100 100
        for (Integer i : integers1) {
            System.out.print(i + " ");
        }
        System.out.println();

        // asList
        List<Integer> integerList = Arrays.asList(integers);
        System.out.println(integerList); // [1, 2, 10, 20, 40, 90, 300, 1000]
    }
}
