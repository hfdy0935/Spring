package com.hspedu.chapter17;

public class Thread04 {
    public static void main(String[] args) {
        AThread st = new AThread();
        new Thread(st).start();
        for (int i = 0; i <= 60; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("main线程运行中..." + i);
            if (i == 30) {
                st.setLoop(false);
            }
        }
    }
}

class AThread implements Runnable {
    boolean loop = true;

    @Override
    public void run() {
        while (loop) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("AThread运行中...");
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}