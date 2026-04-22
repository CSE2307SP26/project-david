package main;

import java.util.ArrayList;
import java.util.List;

public class Loan {
    private double remainingBalance;
    private boolean closed;
    private BankAccount linkedAccount;
    private List<Double> paymentHistory;

    public Loan(double amount, BankAccount account) {
        if (amount <= 0 || account == null) {
            throw new IllegalArgumentException();
        }

        if (account.isClosed() || account.isFrozen()) {
            throw new IllegalStateException("Account not eligible for loan.");
        }

        this.remainingBalance = amount;
        this.closed = false;
        this.linkedAccount = account;
        this.paymentHistory = new ArrayList<>();
        account.deposit(amount);
    }

    public void makePayment(double amount) {
        if (closed) {
            throw new IllegalStateException();
        }

        if (amount <= 0 || amount > remainingBalance) {
            throw new IllegalArgumentException();
        }
        linkedAccount.withdraw(amount);
        paymentHistory.add(amount);
        remainingBalance -= amount;
        if (remainingBalance == 0) {
            closed = true;
        }
    }

    public void addInterest(double rate) {
        if (closed) {
            throw new IllegalStateException("Cannot add interest to a closed loan.");
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        remainingBalance += remainingBalance * rate;
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public boolean isClosed() {
        return closed;
    }

    public BankAccount getLinkedAccount() {
        return linkedAccount;
    }

    public List<Double> getPaymentHistory() {
        return paymentHistory;
    }

    public double getTotalPaid() {
        double totalPaid = 0;
        for (double payment : paymentHistory) {
            totalPaid += payment;
        }
        return totalPaid;
    }
}