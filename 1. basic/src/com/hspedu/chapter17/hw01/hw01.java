package com.hspedu.chapter17.hw01;

import java.util.Scanner;

public class hw01 {
    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        ScanThread scanThread = new ScanThread(printThread);
        Thread t1 = new Thread(printThread);
        Thread t2 = new Thread(scanThread);
        t1.start();
        t2.start();
    }
}

class PrintThread implements Runnable {
    private boolean isFinish = false;

    private synchronized void print() {
        for (int i = 0; i < 100; i++) {
            if (isFinish) return;
            try {
                System.out.println(i);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        print();
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }
}

class ScanThread implements Runnable {
    private final PrintThread printThread;

    public ScanThread(PrintThread printThread) {
        this.printThread = printThread;
    }

    private synchronized void scan() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.next();
            char c = s.charAt(0);
            if (c == 'Q') {
                printThread.setIsFinish(true);
                break;
            }
        }
    }

    @Override
    public void run() {
        scan();
    }
}