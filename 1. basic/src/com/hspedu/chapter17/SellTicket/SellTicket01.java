package com.hspedu.chapter17.SellTicket;

import java.util.concurrent.locks.ReentrantLock;

// 实现Runnable
public class SellTicket01 {
    public static void main(String[] args) {
        SellTicket1 s1 = new SellTicket1();
        new Thread(s1).start();
        new Thread(s1).start();
        new Thread(s1).start();
    }
}

class SellTicket1 implements Runnable {
    private static int ticketNum = 100; // 让多个线程共享
    private static boolean loop = true;
    private static ReentrantLock lock = new ReentrantLock();

    public synchronized void sell() {
        if (ticketNum <= 0) {
            System.out.println("已卖完...");
            loop = false;
            return;
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票" + " 剩余票数=" + (--ticketNum));//1 - 0 - -1 - -2
    }

    // 或者也可以
    public void sell1() {
        synchronized (this) {
            if (ticketNum <= 0) {
                //...
            }
            // try...
        }
    }

    // 或者也可以
    public void sell3() {
        try {
            lock.lock();
            if (ticketNum <= 0) {
                System.out.println("已卖完...");
                loop = false;
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票" + " 剩余票数=" + (--ticketNum));//1 - 0 - -1 - -2
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}