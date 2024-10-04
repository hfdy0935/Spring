package com.hspedu.chapter08.hw08;

public class BankAccount {
    public double balance; // 余额

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // 存款
    public void deposit(double amount) {
        this.balance += amount;
    }

    // 取款
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    // 查询
    public double getBalance() {
        return this.balance;
    }
}
