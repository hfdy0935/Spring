package com.hspedu.chapter08.hw05;

/**
 * 工人、农民、教师、科学家、服务生继承Employee类，分别计算工资
 */
public class hw05 {
    public static void main(String[] args) {
        Waiter zhangsan = new Waiter("张三", 1);
        System.out.println(zhangsan.getYearSalary());
        Teacher lisi = new Teacher("李四", 100, 300, 1);
        System.out.println(lisi.getYearSalary());
        Scientist wangwu = new Scientist("王五", 200, 20000);
        System.out.println(wangwu.getYearSalary());
    }
}

