package com.hspedu.chapter17;

public class Thread03 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
    }
}


class T1 implements Runnable {
    private Integer count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("hello world " + (++count));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (count == 60) {
                break;
            }
        }
    }
}

class T2 implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("hi " + (++count));
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (count == 50) {
                break;
            }
        }
    }
}