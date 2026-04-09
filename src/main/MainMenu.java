package main;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 12;
    private static final int MAX_SELECTION = 12;

    private ArrayList<BankAccount> userAccounts;
    private Scanner keyboardInput;

    public MainMenu() {
        this.userAccounts = new ArrayList<>();
        this.userAccounts.add(new BankAccount());
        this.keyboardInput = new Scanner(System.in);
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
        System.out.println("11. Frozen account");
        System.out.println("12. Exit the app");
    }

    public int getUserSelection(int max) {
        int selection = -1;
        while(selection < 1 || selection > max) {
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
            case 11:
                performFreezeAccount();
                break;
        }
    }

    public void checkBalance() {
        BankAccount account = userAccount();
        System.out.println("Current balance: " + account.getBalance());
    }

    public int getNumberOfAccounts(){
        return userAccounts.size();
    }

    public void createAdditionalAccount() {
        userAccounts.add(new BankAccount());
    }

    public BankAccount userAccount() {
        int selection = -1;
        while(selection < 1 || selection > userAccounts.size()){
            System.out.println("Please select account: ");
            for(int i = 0; i < userAccounts.size(); i++){
                System.out.println((i + 1) + ". " + userAccounts.get(i).getAccountName());
            }
            selection = keyboardInput.nextInt();
        }
        return userAccounts.get(selection - 1);
    }

    public void performDeposit() {
        double depositAmount = -1;
        while(depositAmount < 0) {
            System.out.print("How much would you like to deposit: ");
            depositAmount = keyboardInput.nextInt();
        }
        userAccount().deposit(depositAmount);
    }

    public void performWithDraw(){
        double withDrawAmount = -1;
        while(withDrawAmount < 0 || withDrawAmount > userAccount().getBalance()) {
            System.out.print("How much would you like to withdraw: ");
            withDrawAmount = keyboardInput.nextInt();
        }
        userAccount().withdraw(withDrawAmount);
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
        for(Transaction transaction : account.getTransactionHistory()) {
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

    public void performFreezeAccount() {
        BankAccount selectedAccount = userAccount();
        selectedAccount.freezeAccount();
    }


    public void run() {
        int selection = -1;
        while(selection != EXIT_SELECTION) {
            displayOptions();
            selection = getUserSelection(MAX_SELECTION);
            processInput(selection);
        }
    }

    public static void main(String[] args) {
        MainMenu bankApp = new MainMenu();
        bankApp.run();
    }
    public void viewAccountSummary() {
        BankAccount selectedAccount = userAccount();
        System.out.println(selectedAccount.getAccountSummary());
    }
}