package com.hspedu.chapter17;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Thread12 {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();
        FutureTask futureTask = new FutureTask(numThread);
        Thread thread = new Thread(futureTask);
        thread.start();

        // 最后运行
        try {
            Object sum = futureTask.get();
            System.out.println("总和为" + (int) sum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class NumThread implements Callable {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}