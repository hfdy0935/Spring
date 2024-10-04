package com.hspedu.chapter12;


public class Exception3 {
    public static void main(String[] args) {
        int age = 200;
        if (age > 100) {
            throw new AgeException("年龄不能大于100");
        }
        System.out.println("年龄：" + age);

    }
}

class AgeException extends RuntimeException {
    public AgeException(String msg) {
        super(msg);
    }
}
