package com.hspedu.chapter08.smallChangeSys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 零钱通管理系统
 */
public class SmallChangeSys {
    private boolean loop = true;
    private final Scanner scanner;
    private String key;
    private String details;
    private double money;
    private double balance;
    private final SimpleDateFormat sdf;
    private String note;

    public SmallChangeSys() {
        this.scanner = new Scanner(System.in);
        this.key = "";
        this.details = "-----------------零钱通明细------------------";
        this.money = 0.0;
        this.balance = 0.0;
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.note = "";
    }

    public void start() {
        do {
            System.out.println("\n================零钱通菜单===============");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退     出");
            System.out.print("请选择(1-4): ");
            this.key = scanner.next();
            switch (this.key) {
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.addMoney();
                    break;
                case "3":
                    this.consume();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        } while (this.loop);
        System.out.println("-----已退出零钱通项目-----");
    }

    private String getFormatNowDate() {
        Date date = new Date();
        return this.sdf.format(date);
    }

    private void detail() {
        System.out.println(this.details);
    }

    private void addMoney() {
        System.out.println("输入收益入账金额：");
        this.money = scanner.nextDouble();
        if (this.money <= 0) {
            System.out.println("收益入账金额必须 > 0");
        } else {
            this.balance += this.money;
            this.details += "\n收益入账\t+" + this.money + "\t" + this.getFormatNowDate() + "\t" + this.balance;
        }
    }

    private void consume() {
        System.out.println("输入消费金额：");
        this.money = this.scanner.nextDouble();
        if (this.money > this.balance) {
            System.out.println("余额不足");
        } else if (this.money <= 0) {
            System.out.println("消费金额范围应 > 0");
        } else {
            System.out.println("消费说明：");
            this.note = this.scanner.next();
            this.balance -= this.money;
            this.details += "\n" + note + "\t-" + this.money + "\t" + this.getFormatNowDate() + "\t" + this.balance;
        }
    }

    private void exit() {
        String choice;
        do {
            System.out.println("你确定要退出吗？ y/n");
            choice = this.scanner.next();
        } while (!"y".equals(choice) && !"n".equals(choice));
        if (choice.equals("y")) {
            this.loop = false;
        }
    }
}
