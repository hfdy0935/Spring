package com.hspedu.chapter13.ArraysPractise;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerAndBigDecimalTest {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("9999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        BigInteger add = bigInteger.add(bigInteger);
        System.out.println(add);// 加 19999999999999999999999999999999999999999999999999999999999999999999999999999999998
        BigInteger subtract = bigInteger.subtract(bigInteger);
        System.out.println(subtract);// 减 0
        BigInteger multiply = bigInteger.multiply(bigInteger);
        System.out.println(multiply);// 乘 ......
        BigInteger divide = bigInteger.divide(bigInteger);
        System.out.println(divide);// 除 1

        BigDecimal bigDecimal = new BigDecimal("1999.11111111111999999999999977788");
        System.out.println(bigDecimal.add(bigDecimal)); // 3998.22222222223999999999999955576
        System.out.println(bigDecimal.subtract(bigDecimal)); // 0E-29
        System.out.println(bigDecimal.multiply(bigDecimal)); // 3996445.2345679367743209876535119148799999960512000000000493372944
        // 可能抛出异常 ArithmeticException
        // 指定精度，BigDecimal.ROUND_CEILING，如果有无限循环小数，就会保留 分子 的精度
        System.out.println(bigDecimal.divide(bigDecimal, BigDecimal.ROUND_CEILING)); // 1.00000000000000000000000000000
    }
}
