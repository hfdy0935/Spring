package com.hspedu.chapter08.hw04;

public class hw04 {
    public static void main(String[] args) {
        Manager manager = new Manager("张三", 3.0, 100);
        Worker worker = new Worker("李四", 2.0, 100);
        System.out.println(manager.printSalary());
        System.out.println(worker.printSalary());
    }
}



