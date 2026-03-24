package main;

public class BankAccount {

    private double balance;
    private boolean closed;

    public BankAccount() {
        this.balance = 0;
        this.closed = false;
    }

    public void deposit(double amount) {
        if (closed) {
            throw new IllegalStateException("Cannot deposit into a closed account.");
        }

        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException();
        }
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
}