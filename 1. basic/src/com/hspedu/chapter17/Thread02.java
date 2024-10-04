package com.hspedu.chapter17;

public class Thread02 {
    public static void main(String[] args) {
        Dog dog=new Dog();
        dog.run();
    }
}


class ThreadProxy implements Runnable {
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();
    }

    public void start0() {
        run();
    }
}

class Dog implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("dog叫 " + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (count == 10) {
                break;
            }
        }
    }
}