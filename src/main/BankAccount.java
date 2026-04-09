package main;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private boolean closed;
    private List<Transaction> transactionHistory;
    private double withdrawLimit;
    private String accountName;
    private double minimumBalance;
    private boolean frozen;
    private double loanBalance;

    public BankAccount() {
        this.balance = 0;
        this.closed = false;
        this.transactionHistory = new ArrayList<>();
        this.withdrawLimit = Double.MAX_VALUE;
        this.accountName = "Account";
        this.minimumBalance = 0;
        this.frozen = false;
        this.loanBalance = 0;
    }

    public void deposit(double amount) {
        if (closed || frozen) {
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
        if (closed || frozen) {
            throw new IllegalStateException();
        }
        if (amount > 0 && amount <= this.balance && this.balance - amount >= this.minimumBalance && amount <= this.withdrawLimit) {
            this.balance -= amount;
            transactionHistory.add(new Transaction("withdraw", amount));
        } else {
            throw new IllegalArgumentException();
        }
    }
    public void setMinimumBalance(double minimumBalance) {
        if (minimumBalance < 0) {
            throw new IllegalArgumentException();
        }
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return this.minimumBalance;
    }

    public void addInterest(double rate) {
        if (closed || frozen) {
            throw new IllegalStateException();
        }
        if (rate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        double interestPayment = this.balance * rate;
        this.balance += interestPayment;
    }

    public void transferMoney(BankAccount targetAccount, double amount) {
        if (this.frozen || targetAccount.isFrozen()) {
            throw new IllegalStateException();
        }
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
        if (closed || frozen) {
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

    public void setAccountName(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.accountName = name;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public String getAccountSummary() {
    double totalDeposits = 0;
    double totalWithdrawals = 0;

    for (Transaction transaction : transactionHistory) {
        if (transaction.getType().equals("deposit")) {
            totalDeposits += transaction.getAmount();
        } else if (transaction.getType().equals("withdraw")) {
            totalWithdrawals += transaction.getAmount();
        }
    }

    return "Current balance: $" + this.balance
            + "\nTotal deposits: $" + totalDeposits
            + "\nTotal withdrawals: $" + totalWithdrawals
            + "\nMinimum balance: $" + this.minimumBalance;
    }

    public void freezeAccount() {
        if (closed) {
            throw new IllegalStateException();
        }
        this.frozen = true;
    }

    public void unfreezeAccount() {
        if (closed) {
            throw new IllegalStateException();
        }
        this.frozen = false;
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    public void applyForLoan(double amount) {
        if (closed || frozen) {
            throw new IllegalStateException();
        }
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        this.loanBalance += amount;
        this.balance += amount;
        transactionHistory.add(new Transaction("loan", amount));
    }
    
    public void payOffLoan(double amount) {
        if (closed || frozen) {
            throw new IllegalStateException();
        }
        if (amount <= 0 || amount > this.loanBalance || amount > this.balance
                || this.balance - amount < this.minimumBalance || amount > this.withdrawLimit) {
            throw new IllegalArgumentException();
        }
        this.loanBalance -= amount;
        this.balance -= amount;
        transactionHistory.add(new Transaction("loan payment", amount));
    }
    
    public double getLoanBalance() {
        return this.loanBalance;
    }
    
}