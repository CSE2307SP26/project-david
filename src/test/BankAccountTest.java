package test;
import main.Transaction;
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

    @Test
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
            //do nothing, test passes
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
            //do nothing, test passes
        }
    }

    @Test
    public void testTransferMoney() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();

        fromAccount.deposit(100);
        fromAccount.transferMoney(toAccount, 50);

        assertEquals(50, fromAccount.getBalance(), 0.01);
        assertEquals(50, toAccount.getBalance(), 0.01);
    }

    @Test
    public void testOverTransferMoney() {
        BankAccount fromAccount = new BankAccount();
        BankAccount toAccount = new BankAccount();

        fromAccount.deposit(50);

        try {
            fromAccount.transferMoney(toAccount, 100);
            fail();
        } catch (IllegalArgumentException e) {
            //do nothing, test passes
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
            //do nothing, test passes
        }
    }
}
