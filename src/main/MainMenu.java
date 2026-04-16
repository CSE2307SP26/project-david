package main;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 16;
    private static final int MAX_SELECTION = 16;

    private ArrayList<BankAccount> userAccounts;
    private Scanner keyboardInput;
    private ArrayList<Loan> userLoans;

    public MainMenu() {
        this.userAccounts = new ArrayList<>();
        this.userAccounts.add(new BankAccount());
        this.keyboardInput = new Scanner(System.in);
        this.userLoans = new ArrayList<>();
    }

    public void displayOptions() {
        System.out.println("Welcome to the 237 Bank App!");
        System.out.println("1. Make a deposit");
        System.out.println("2. Check account balance");
        System.out.println("3. Make a withdraw");
        System.out.println("4. Create a new account");
        System.out.println("5. View transaction history");
        System.out.println("6. Add interest payment");
        System.out.println("7. Close existing account");
        System.out.println("8. Set withdraw limit");
        System.out.println("9. Rename account");
        System.out.println("10. View account summary");
        System.out.println("11. Freeze account");
        System.out.println("12. Apply for a loan");
        System.out.println("13. Check remaining loan balance");
        System.out.println("14. Pay off a loan");
        System.out.println("15. Unfreeze account");
        System.out.println("16. Exit the app");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while (selection < 1 || selection > max) {
            System.out.print("Please make a selection: ");
            selection = keyboardInput.nextInt();
        }
        return selection;
    }

    public void processInput(int selection) {
        switch (selection) {
            case 1:
                performDeposit();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                performWithDraw();
                break;
            case 4:
                createAdditionalAccount();
                break;
            case 5:
                viewTransactionHistory();
                break;
            case 6:
                performAddInterest();
                break;
            case 7:
                performCloseAccount();
                break;
            case 8:
                setWithdrawLimit();
                break;
            case 9:
                renameAccount();
                break;
            case 10:
                viewAccountSummary();
                break;
            case 11:
                performFreezeAccount();
                break;
            case 12:
                performApplyLoan();
                break;
            case 13:
                checkRemainingLoanBalance();
                break;
            case 14:
                performPayOffLoan();
                break;
            case 15:
                performUnfreezeAccount();
                break;
            case 16:
                break;
        }
    }

    public void checkBalance() {
        BankAccount account = userAccount();
        System.out.println("Current balance: " + account.getBalance());
    }

    public int getNumberOfAccounts() {
        return userAccounts.size();
    }

    public void createAdditionalAccount() {
        userAccounts.add(new BankAccount());
    }

    public BankAccount userAccount() {
        int selection = -1;
        while (selection < 1 || selection > userAccounts.size()) {
            System.out.println("Please select account: ");
            for (int i = 0; i < userAccounts.size(); i++) {
                System.out.println((i + 1) + ". " + userAccounts.get(i).getAccountName());
            }
            selection = keyboardInput.nextInt();
        }
        return userAccounts.get(selection - 1);
    }

    public Loan userLoan() {
        if (userLoans.isEmpty()) {
            System.out.println("No loans available.");
            return null;
        }
        int selection = -1;
        while (selection < 1 || selection > userLoans.size()) {
            System.out.println("Please select loan: ");
            for (int i = 0; i < userLoans.size(); i++) {
                System.out.println((i + 1) + ". Loan for account: " 
                        + userLoans.get(i).getLinkedAccount().getAccountName()
                        + " | Remaining balance: $" 
                        + userLoans.get(i).getRemainingBalance());
            }
            selection = keyboardInput.nextInt();
        }
        return userLoans.get(selection - 1);
    }


    public void performDeposit() {
        double depositAmount = -1;
        while (depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount().deposit(depositAmount);
    }

    public void performWithDraw() {
        BankAccount account = userAccount();
        double withDrawAmount = -1;
        while (withDrawAmount < 0 || withDrawAmount > account.getBalance()) {
            System.out.print("How much would you like to withdraw: ");
            withDrawAmount = keyboardInput.nextDouble();
        }
        account.withdraw(withDrawAmount);
    }

    public void setWithdrawLimit() {
        BankAccount account = userAccount();
        System.out.print("Enter withdraw limit: ");
        double limit = keyboardInput.nextDouble();
        account.setWithdrawLimit(limit);
        System.out.println("Withdraw limit set to: " + limit);
    }

    public void viewTransactionHistory() {
        BankAccount account = userAccount();
        System.out.println("Transaction history: ");
        for (Transaction transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public void performAddInterest() {
        BankAccount selectedAccount = userAccount();
        double interestRate = -1;
        while (interestRate < 0) {
            System.out.print("Enter the interest rate: ");
            interestRate = keyboardInput.nextDouble();
        }
        selectedAccount.addInterest(interestRate);
    }

    public void performCloseAccount() {
        BankAccount selectedAccount = userAccount();
        selectedAccount.closeAccount();
    }

    public void renameAccount() {
        BankAccount account = userAccount();
        System.out.print("Enter new account name: ");
        String name = keyboardInput.next();
        account.setAccountName(name);
        System.out.println("Account renamed to: " + name);
    }

    public void viewAccountSummary() {
        BankAccount selectedAccount = userAccount();
        System.out.println(selectedAccount.getAccountSummary());
    }

    public void performFreezeAccount() {
        BankAccount selectedAccount = userAccount();
        selectedAccount.freezeAccount();
    }

    public void performApplyLoan() {
        BankAccount account = userAccount();
        if (account.isClosed() || account.isFrozen()) {
            System.out.println("This account cannot be used for a loan.");
            return;
        }
        double loanAmount = -1;
        while (loanAmount <= 0) {
            System.out.print("Enter loan amount: ");
            loanAmount = keyboardInput.nextDouble();
        }
        userLoans.add(new Loan(loanAmount, account));
        System.out.println("Loan created and deposited into account.");
    }

    public void checkRemainingLoanBalance() {
        Loan selectedLoan = userLoan();
        if (selectedLoan != null) {
            System.out.println("Remaining loan balance: " + selectedLoan.getRemainingBalance());
        }
    }

    public void performPayOffLoan() {
        Loan loan = userLoan();
        if (loan == null) return;

        double paymentAmount = -1;
        while (paymentAmount <= 0) {
            System.out.print("Enter payment amount: ");
            paymentAmount = keyboardInput.nextDouble();
        }

        try {
            loan.makePayment(paymentAmount);
            System.out.println("Payment successful.");
            System.out.println("Remaining loan balance: " + loan.getRemainingBalance());
            if (loan.isClosed()) {
                userLoans.remove(loan);
                System.out.println("Loan has been fully paid off and closed.");
            }
        } catch (Exception e) {
            System.out.println("Payment failed: " + e.getMessage());
        }
    }

    public void performUnfreezeAccount(){
        BankAccount selectedAccount = userAccount();
        selectedAccount.unfreezeAccount();
    }

    public void run() {
        int selection = -1;
        while (selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
}