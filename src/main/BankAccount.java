package main;

public class BankAccount {

    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void addInterest(double rate) {
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }

        double interestPayment = this.balance * rate;
        this.balance += interestPayment;
    }


    public double getBalance() {
        return this.balance;
    }
}