package test;
import main.Transaction;
import main.BankAccount;
import main.Loan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void testFreezeAccount() {
        BankAccount account = new BankAccount();
        account.freezeAccount();
        assertTrue(account.isFrozen());
    }

    @Test
    public void testCannotDepositWhenFrozen() {
        BankAccount account = new BankAccount();
        account.freezeAccount();
        try {
            account.deposit(50);
            fail();
        } catch (IllegalStateException e) {
        }
    }


    @Test
    public void testCannotWithdrawWhenFrozen() {
        BankAccount account = new BankAccount();
        account.deposit(100);
        account.freezeAccount();

        try {
            account.withdraw(20);
            fail();
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void testUnfreezeAccount() {
        BankAccount account = new BankAccount();
        account.freezeAccount();
        account.unfreezeAccount();
        assertFalse(account.isFrozen());
    }

    @Test
    public void testCanDepositAfterUnfreeze() {
        BankAccount account = new BankAccount();
        account.freezeAccount();
        account.unfreezeAccount();
        account.deposit(100);
        assertEquals(100, account.getBalance(), 0.001);
    }


    @Test
    public void testSetDepositLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.setDepositLimit(100);
        try {
            testAccount.deposit(150);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testDepositWithinLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.setDepositLimit(100);
        testAccount.deposit(50);
        assertEquals(50, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testSetWithdrawCountLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.setWithdrawCountLimit(1);
        testAccount.withdraw(10);
        try {
            testAccount.withdraw(10);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testWithdrawWithinCountLimit() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(100);
        testAccount.setWithdrawCountLimit(2);
        testAccount.withdraw(10);
        testAccount.withdraw(10);
        assertEquals(80, testAccount.getBalance(), 0.01);
    }

   @Test
    public void testSetSavingsGoal() {
        BankAccount testAccount = new BankAccount();
        testAccount.setSavingsGoal(500);
        assertEquals(500, testAccount.getSavingsGoal(), 0.001);
    }

    @Test
    public void testInvalidSavingsGoal() {
        BankAccount testAccount = new BankAccount();
        assertThrows(IllegalArgumentException.class, () -> testAccount.setSavingsGoal(0));
    }

    @Test
    public void testRemainingToGoal() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(200);
        testAccount.setSavingsGoal(500);
        assertEquals(300, testAccount.getRemainingToGoal(), 0.001);
    }

    @Test
    public void testSavingsProgress() {
        BankAccount testAccount = new BankAccount();
        testAccount.deposit(200);
        testAccount.setSavingsGoal(500);
        assertEquals(40, testAccount.getSavingsProgress(), 0.001);
    }




}
