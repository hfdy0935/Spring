package com.hspedu.chapter17;

public class Thread05 {
    public static void main(String[] args) throws InterruptedException {
        T3 t3 = new T3();
        t3.start();
//        t3.join();
        for (int i = 1; i <= 20; i++) {
            // 8-15暂停，其他区间运行
            t3.setIsPause(i >= 8 && i <= 15);
            Thread.sleep(50);
            System.out.println("张三丰" + i);
        }
    }
}

class T3 extends Thread {
    private boolean isPause = false;

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            while (true) {
                try {
                    if (isPause) {
                        interrupt();
                    }
                    Thread.sleep(50);
                    System.out.println("JoinThread------" + i);
                    break;
                } catch (InterruptedException e) {
//                System.out.println(e.getMessage());
                }
            }
        }
    }

    public void setIsPause(boolean isPause) {
        this.isPause = isPause;
    }
}