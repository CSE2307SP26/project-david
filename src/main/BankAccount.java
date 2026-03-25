package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private List<Transaction> transactionHistory;

    public BankAccount() {
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
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
        if(amount > 0 && amount <= this.balance){
            this.balance -= amount;
            transactionHistory.add(new Transaction("withdraw", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public void transferMoney(BankAccount targetAccount, double amount) {
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public void collectFee(double amount) {
        this.withdraw(amount);
    }
    
    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}