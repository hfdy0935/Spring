package com.hspedu.chapter17;

public class Thread01 {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
//        cat1.run();
        cat1.start();
        Cat cat2 = new Cat();
//        cat2.run();
        cat2.start();
    }
}


class Cat extends Thread {
    int times = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("-------------------------");
                System.out.println(e.getMessage());
            }
            System.out.println("Hello World" + (++times));
            if (times == 5) {
                interrupt();
            }
            if (times == 10) {
                break;
            }
        }
    }
}