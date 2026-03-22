package main;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    private static final int EXIT_SELECTION = 3;
	private static final int MAX_SELECTION = 3;

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
        System.out.println("2. Exit the app");
        System.out.println("4. Create a new account");
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
            case 4:
                createAdditionalAccount();
                break;
        }
    }

    public int getNumberOfAccounts(){
        return userAccounts.size();
    }
    
    public void createAdditionalAccount() {
        userAccounts.add(new BankAccount());
    }

    public BankAccount chooseAccount() {
        int selection = -1;
        while(selection < 1 || selection > userAccounts.size()){
            System.out.println("Please select account: ");
            for(int i = 0; i < userAccounts.size(); i++){
                System.out.println((i + 1) + ". Account" + (i + 1));
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
        chooseAccount().deposit(depositAmount);
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

}
