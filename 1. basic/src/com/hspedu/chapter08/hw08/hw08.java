package com.hspedu.chapter08.hw08;

public class hw08 {
    public static void main(String[] args) {
        SavingsAccount savingAccount = new SavingsAccount(0);
        savingAccount.deposit(0.2);
        savingAccount.deposit(0.2);
        savingAccount.deposit(0.2);
        savingAccount.handleinterest();
        savingAccount.handleinterest();
        savingAccount.handleinterest();
        savingAccount.handleinterest();
        savingAccount.handleinterest();
        savingAccount.handleinterest();
        savingAccount.deposit(0.2);
        System.out.println(savingAccount.getBalance());
    }
}
