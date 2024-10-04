package com.hspedu.chapter08.hw08;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("存款必须 > 0");
        } else if (this.balance + amount < 1) {
            System.out.println("存款失败，余额不足以付手续费");
        } else {
            System.out.println("存款成功，已收取1美元手续费");
            this.balance += (amount - 1);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("取款失败，余额不足");
        } else if (amount > this.balance - 1) {
            System.out.println("取款失败，余额不足以付手续费");
        } else {
            System.out.println("取款成功，已收取1美元手续费");
            this.balance -= (amount + 1);
        }
    }
}
