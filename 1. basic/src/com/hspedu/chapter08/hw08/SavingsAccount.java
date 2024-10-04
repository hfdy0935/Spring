package com.hspedu.chapter08.hw08;

public class SavingsAccount extends BankAccount {
    private int chargeNum; // 本月交易次数

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    /**
     * 重置免手续费
     */
    public void earnMonthlyInterest() {
        this.chargeNum = 0;
    }

    public void handleinterest() {
        this.balance *= 1.1;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("存款必须 > 0");
            return;
        }
        if (this.chargeNum < 3) {
            // 有免手续费次数
            System.out.println("存款成功，本次免收手续费");
            this.balance += amount;
            this.chargeNum++;
        } else {
            // 没次数了
            if (this.balance + amount < 1) {
                System.out.println("存款失败，余额不足以付手续费");
            } else {
                System.out.println("存款成功，已收取1美元手续费");
                this.balance += (amount - 1);
                this.chargeNum++;
            }
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("取款失败，余额不足");
            return;
        }
        if (this.chargeNum < 3) {
            // 有免手续费次数
            System.out.println("取款成功，本次免收手续费");
            this.balance -= amount;
            this.chargeNum++;
        } else {
            // 没次数了
            if (amount > this.balance - 1) {
                System.out.println("取款失败，余额不足以付手续费");
            } else {
                System.out.println("取款成功，已收取1美元手续费");
                this.balance -= (amount + 1);
                this.chargeNum++;
            }
        }
    }

}
