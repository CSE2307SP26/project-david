package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.BankAccount;
import main.MainMenu;

public class MainMenuTest {

    @Test
    public void testCreateAdditionalAccount() {
        MainMenu menu = new MainMenu();
        assertEquals(1, menu.getNumberOfAccounts());
        menu.createAdditionalAccount();
        assertEquals(2, menu.getNumberOfAccounts());
    }

    
    @Test
    public void testCheckBalance() {
        BankAccount account = new BankAccount();
        
        account.deposit(100.0);
        
        assertEquals(100.0, account.getBalance(), 0.001);
    }
}