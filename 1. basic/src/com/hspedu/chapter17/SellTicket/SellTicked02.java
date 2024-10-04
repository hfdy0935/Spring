package com.hspedu.chapter17.SellTicket;

import java.util.concurrent.locks.ReentrantLock;

// 继承Thread
public class SellTicked02 {
    public static void main(String[] args) {
        Thread t1 = new SellTicket2();
        Thread t2 = new SellTicket2();
        Thread t3 = new SellTicket2();
        t1.start();
        t2.start();
        t3.start();
    }
}

class SellTicket2 extends Thread {
    private static int ticketNum = 100;
    private boolean loop = true;
    private static final ReentrantLock lock = new ReentrantLock();


    // 也可以用synchronized
    public void sell() {
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