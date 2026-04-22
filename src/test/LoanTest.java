package test;

import main.BankAccount;
import main.Loan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class LoanTest {
    @Test
    public void testApplyForLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(500, testAccount);
        assertEquals(500, testLoan.getRemainingBalance(), 0.01);
        assertFalse(testLoan.isClosed());
        assertEquals(testAccount, testLoan.getLinkedAccount());
        assertEquals(500, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testInvalidLoanAmount() {
        BankAccount testAccount = new BankAccount();
        try {
            Loan testLoan = new Loan(-100, testAccount);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testApplyForLoanToClosedAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.closeAccount();
        try {
            Loan testLoan = new Loan(200, testAccount);
            fail();
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void testApplyForLoanToFrozenAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.freezeAccount();
        try {
            Loan testLoan = new Loan(200, testAccount);
            fail();
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void testCheckRemainingLoanBalance() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(250, testAccount);
        assertEquals(250, testLoan.getRemainingBalance(), 0.01);
    }

    @Test
    public void testPayOffLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(500, testAccount);
        testLoan.makePayment(200);
        assertEquals(300, testLoan.getRemainingBalance(), 0.01);
        assertEquals(300, testAccount.getBalance(), 0.01);
    }

    @Test
    public void testOverPayLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(300, testAccount);
        try {
            testLoan.makePayment(400);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testLoanPaymentTransactionHistory() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(300, testAccount);
        testLoan.makePayment(100);
        assertEquals(2, testAccount.getTransactionHistory().size());
        assertEquals("withdraw", testAccount.getTransactionHistory().get(1).getType());
        assertEquals(100, testAccount.getTransactionHistory().get(1).getAmount(), 0.01);
    }

    @Test
    public void testAddInterestToLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(200, testAccount);
        testLoan.addInterest(0.10);
        assertEquals(220, testLoan.getRemainingBalance(), 0.01);
    }

    @Test
    public void testAddNegativeInterestToLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(200, testAccount);
        try {
            testLoan.addInterest(-0.10);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testAddInterestToClosedLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(200, testAccount);
        testLoan.makePayment(200);
        try {
            testLoan.addInterest(0.10);
            fail();
        } catch (IllegalStateException e) {
        }
    }

    @Test
    public void testLoanPaymentHistoryAfterOnePayment() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(500, testAccount);
        testAccount.deposit(200);
        testLoan.makePayment(100);

        assertEquals(1, testLoan.getPaymentHistory().size());
        assertEquals(100, testLoan.getPaymentHistory().get(0), 0.001);
    }

    @Test
    public void testLoanPaymentHistoryAfterMultiplePayments() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(500, testAccount);
        testAccount.deposit(300);
        testLoan.makePayment(100);
        testLoan.makePayment(150);

        assertEquals(2, testLoan.getPaymentHistory().size());
        assertEquals(100, testLoan.getPaymentHistory().get(0), 0.001);
        assertEquals(150, testLoan.getPaymentHistory().get(1), 0.001);
    }

    @Test
    public void testTotalPaidOnLoan() {
        BankAccount testAccount = new BankAccount();
        Loan testLoan = new Loan(500, testAccount);
        testAccount.deposit(300);
        testLoan.makePayment(100);
        testLoan.makePayment(150);

        assertEquals(250, testLoan.getTotalPaid(), 0.001);
    }
}