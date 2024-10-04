package com.hspedu.chapter17;

public class Thread08 {
    public static void main(String[] args) {
        SellTicket1 s1 = new SellTicket1();
        SellTicket1 s2 = new SellTicket1();
        SellTicket1 s3 = new SellTicket1();
        // 会出现超卖
        s1.start();
        s2.start();
        s3.start();
    }
}


class SellTicket1 extends Thread {
    private static int ticketNum = 100;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                System.out.println("已卖完");
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票" + " 剩余票数=" + (--ticketNum));
        }
    }
}