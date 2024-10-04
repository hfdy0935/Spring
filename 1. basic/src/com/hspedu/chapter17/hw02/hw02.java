package com.hspedu.chapter17.hw02;

public class hw02 {
    public static void main(String[] args) {
        CreditCard c = new CreditCard(1000);
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}

class CreditCard implements Runnable {
    private double money = 10000;
    private final double speed;

    public CreditCard(double speed) {
        this.speed = speed;

    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (money <= 0) {
                    System.out.println("余额不足...");
                    return;
                }
                try {
                    money -= speed;
                    Thread.sleep(300);
                    System.out.println("用户" + Thread.currentThread().getName() + "取走了" + speed + "，余额" + money);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}