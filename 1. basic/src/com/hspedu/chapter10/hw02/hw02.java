package com.hspedu.chapter10.hw02;

public class hw02 {
    public static void main(String[] args) {
        System.out.println(Frock.getNextNum());
        System.out.println(Frock.getNextNum());
        Frock f1 = new Frock();
        Frock f2 = new Frock();
        Frock f3 = new Frock();
        System.out.println(f1.getSerialNumber());
        System.out.println(f2.getSerialNumber());
        System.out.println(f3.getSerialNumber());
    }
}

class Frock {
    private static int currentNum = 100000;
    private int serialNumber;

    public static int getNextNum() {
        currentNum += 100;
        return currentNum;
    }

    public Frock() {
        serialNumber = getNextNum();
    }

    public int getSerialNumber() {
        return serialNumber;
    }
}
