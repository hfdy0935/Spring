package com.hspedu.chapter08.hw10;

public class hw10 {
    public static void main(String[] args) {
        Doctor doctor1 = new Doctor("jack", 20, "牙科医生", '男', 20000.0);
        Doctor doctor2 = new Doctor("jack", 20, "牙科医生", '男', 20000.0);
        System.out.println(doctor1.equals(doctor2));
    }
}
