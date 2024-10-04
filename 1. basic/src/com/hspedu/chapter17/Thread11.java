package com.hspedu.chapter17;


public class Thread11 {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        p1.setName("生产者1");
        Producer p2 = new Producer(clerk);
        p2.setName("生产者2");
        Consumer c1 = new Consumer(clerk);
        c1.setName("消费者1");

        p1.start();
        c1.start();
    }
}


class Clerk {
    private int productNum = 0;

    public synchronized void addProduct() {
        if (productNum >= 20) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        // 小于20了会自动从上次中断的地方继续执行
        productNum++;
        System.out.println(Thread.currentThread().getName() + "生产了第" + productNum + "个产品");
        notify();
    }

    public synchronized void minusProduct() {
        if (productNum <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费了第" + productNum + "个产品");
        productNum--;
        notify();
    }
}

class Producer extends Thread {
    private final Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            clerk.addProduct();

        }
    }
}

class Consumer extends Thread {
    private final Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            clerk.minusProduct();
        }
    }
}