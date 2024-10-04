package com.hspedu.chapter17;

// 单例模式（线程安全），饿汉式
public class Thread13 {
    static Bank b1;
    static Bank b2;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };
        t2.start();
        // 保证线程t1、t2都在主线程打印语句之前执行
        t1.join();
        t2.join();

        //
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);
    }
}


class Bank {
    private Bank bank;
    private static Bank instance = null;

    public static Bank getInstance() {
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}