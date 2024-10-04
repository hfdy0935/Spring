package com.hspedu.chapter17;

public class Thread06 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new T5());
        for (int i = 0; i < 10; i++) {
            System.out.println("hi " + i);
            if (i == 5) {
                t.start();
                t.join();
            }
            Thread.sleep(1000);
        }
    }
}

class T5 implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("hello " + (++count));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}