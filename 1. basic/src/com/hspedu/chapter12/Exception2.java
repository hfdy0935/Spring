package com.hspedu.chapter12;

import java.util.Scanner;

public class Exception2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        String inputStr = "";
        System.out.print("输入一个整数");
        while (true) {
            inputStr = sc.next();
            try {
                num = Integer.parseInt(inputStr);
                break;
            } catch (NumberFormatException e) {
                System.out.print("输入的不是一个整数，请重新输入：");
            }
        }
        System.out.println("输入的值是：" + num);
    }
}
