package test;

import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
        }
    }
    @Test
    public void testCloseAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();
        assertTrue(testAccount.isClosed());
    }

    @Test
    public void testDepositIntoClosedAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();

        try {
            testAccount.deposit(20);
            fail();
        } catch (IllegalStateException e) {
            
        }
    }
}

