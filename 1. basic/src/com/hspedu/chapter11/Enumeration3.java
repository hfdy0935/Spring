package com.hspedu.chapter11;

public class Enumeration3 {
    public static void main(String[] args) {
        Gender boy = Gender.BOY;
        System.out.println(boy);
    }
}

enum Gender {
    BOY, GIRL;

//    @Override
//    public String toString() {
//        return "1";
//    }
}