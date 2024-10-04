package com.hspedu.chapter12;

import java.io.FileInputStream;
import java.io.IOException;

public class Exception1 {
    public static void main(String[] args) {
        try {
            Test1 myException = new Test1();
        } catch (ArithmeticException e) {
            System.out.println(e);
//            System.out.println(e.getClass());
        }
        try {
            Test2.test();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class Test1 {
    public Test1() throws ArithmeticException {
        System.out.println(1 / 0);
    }
}

class Test2 {
    public static void test() throws IOException {
        FileInputStream fis = new FileInputStream("Z://not/exists/file.txt");
        int len = fis.read();
        System.out.println(len);
        fis.close();
    }
}