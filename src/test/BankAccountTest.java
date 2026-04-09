package test;
import main.Transaction;
import main.BankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidDeposit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.deposit(-50);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
    }

    @Test
    public void testWithDraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        testAccount.withdraw(20);
        assertEquals(30, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testTransactionHistory() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(30);
        assertEquals(2, testAccount.getTransactionHistory().size());
        assertEquals("deposit", testAccount.getTransactionHistory().get(0).getType());
        assertEquals("withdraw", testAccount.getTransactionHistory().get(1).getType());
    }

    @Test
    public void testEmptyWithDraw() {
        BankAccount testAccount = new BankAccount();
        try{
            testAccount.withdraw(50);
            fail();
        } catch (IllegalArgumentException e) {
           
        }
    }

    @Test
    public void testOverWithDraw() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        try{
            testAccount.withdraw(100);
            fail();
        } catch (IllegalArgumentException e) {
  
        }
    }

    @Test
    public void testSetWithdrawLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.setWithdrawLimit(30);
        try {
            testAccount.withdraw(50);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testWithdrawWithinLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.setWithdrawLimit(30);
        testAccount.withdraw(20);
        assertEquals(80, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidWithdrawLimit() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.setWithdrawLimit(-10);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCollectFee() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.collectFee(20);
        assertEquals(80, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testOverCollectFee() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(50);
        try {
            testAccount.collectFee(100);
            fail();
        } catch (IllegalArgumentException e) {
            
        }
    }

    @Test
    public void testAddInterest() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.addInterest(0.05);
        assertEquals(105, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidInterestRate() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.addInterest(-0.10);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testCloseAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();
        assertTrue(testAccount.isClosed());
    }
    @Test
    public void testRenameAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.setAccountName("Savings");
        assertEquals("Savings", testAccount.getAccountName());
    }


    @Test
    public void testInvalidAccountName() {
        BankAccount testAccount = new BankAccount();
        try {
            testAccount.setAccountName("");
            fail();
        } catch (IllegalArgumentException e) {
        }
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
    @Test
    public void testSetMinimumBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.setMinimumBalance(20);
        assertEquals(20, testAccount.getMinimumBalance(), 0.01);
    }

    @Test
    public void testWithdrawBelowMinimumBalance() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.setMinimumBalance(30);

        try {
            testAccount.withdraw(80);
            fail();
        } catch (IllegalArgumentException e) {
         
        }
    }
    @Test
    public void testAccountSummary() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.withdraw(20);
        testAccount.setMinimumBalance(10);

        String summary = testAccount.getAccountSummary();

        assertTrue(summary.contains("Current balance: $80.0"));
        assertTrue(summary.contains("Total deposits: $100.0"));
        assertTrue(summary.contains("Total withdrawals: $20.0"));
        assertTrue(summary.contains("Minimum balance: $10.0"));
    }
}