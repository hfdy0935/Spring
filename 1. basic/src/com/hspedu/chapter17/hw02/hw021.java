package com.hspedu.chapter17.hw02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class hw021 {
    public static void main(String[] args) {
        CreditCard1 c1 = new CreditCard1(1000);
        new Thread(c1).start();
        new Thread(c1).start();
    }
}

class CreditCard1 implements Runnable {
    private double money = 10000;
    private final double speed;
    private static final ReentrantLock lock = new ReentrantLock();

    public CreditCard1(double speed) {
        this.speed = speed;
    }

    private void consume() {
        while (true) {
            try {
                lock.lock();
                if (money <= 0) {
                    System.out.println("余额不足...");
                    return;
                }
                try {
                    Thread.sleep(300);
                    money -= speed;
                    System.out.println("用户" + Thread.currentThread().getName() + "取走了" + speed + "，余额" + money);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void run() {
        consume();
    }
}