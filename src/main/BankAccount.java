package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean closed;
    private List<Transaction> transactionHistory;
    private double withdrawLimit;

    public BankAccount() {
        this.balance = 0;
        this.closed = false;
        this.transactionHistory = new ArrayList<>();
        this.withdrawLimit = Double.MAX_VALUE;
    }

    public void deposit(double amount) {
        if (closed) {
            throw new IllegalStateException();
        }
        if(amount > 0) {
            this.balance += amount;
            transactionHistory.add(new Transaction("deposit", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void withdraw(double amount){
        if (closed) {
            throw new IllegalStateException();
        }
        if(amount > 0 && amount <= this.balance && amount <= this.withdrawLimit){
            this.balance -= amount;
            transactionHistory.add(new Transaction("withdraw", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addInterest(double rate) {
        if (closed) {
            throw new IllegalStateException();
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        double interestPayment = this.balance * rate;
        this.balance += interestPayment;
    }

    public void transferMoney(BankAccount targetAccount, double amount) {
        this.withdraw(amount);
        targetAccount.deposit(amount);
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

    public void collectFee(double amount) {
        if (closed) {
            throw new IllegalStateException();
        }
        this.withdraw(amount);
    }

    public void closeAccount() {
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}