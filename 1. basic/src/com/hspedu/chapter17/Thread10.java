package com.hspedu.chapter17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Thread10 {
    public static void main(String[] args) {
        // 10个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) executorService;
        // 设置属性，线程数上限
        service1.setMaximumPoolSize(50);
        // 执行指定线程
        // Runnable
        service1.execute(new NumberThread1());
        service1.execute(new NumberThread2());
        // Callable
//        service1.submit(Callable,Callable);
        // 关闭
        service1.shutdown();
    }
}


class NumberThread1 implements Runnable {
    private int num = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("线程" + Thread.currentThread().getName() + "的第" + (++num) + "次执行");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (num == 20) {
                break;
            }
        }
    }
}

class NumberThread2 implements Runnable {
    private int num = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                System.out.println("线程" + Thread.currentThread().getName() + "的第" + (++num) + "次执行");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (num == 20) {
                break;
            }
        }
    }
}