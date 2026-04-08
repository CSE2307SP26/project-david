package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Transaction> transactionHistory;
    private double withdrawLimit;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
        this.withdrawLimit = Double.MAX_VALUE;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add(new Transaction("deposit", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount){
        if(amount > 0 && amount <= this.balance && amount <= this.withdrawLimit){
            this.balance -= amount;
            transactionHistory.add(new Transaction("withdraw", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setWithdrawLimit(double limit) {
        if(limit > 0) {
            this.withdrawLimit = limit;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public double getWithdrawLimit() {
        return this.withdrawLimit;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}