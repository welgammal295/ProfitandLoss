package com.welgammal.walid.profitandloss;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class LoanActivityTest {
    private LoanActivity loanActivity;

    @Before
    public void setUp() {
        loanActivity = new LoanActivity();
        loanActivity.editTextLoanAmount = mock(EditText.class);
        loanActivity.editTextDownPayment = mock(EditText.class);
        loanActivity.editTextNumberOfPayments = mock(EditText.class);
        loanActivity.editTextInterestRate = mock(EditText.class);
        loanActivity.textViewMonthlyPayment = mock(TextView.class);
        loanActivity.textViewDownPayment = mock(TextView.class);
    }

    @After
    public void tearDown() {
        loanActivity = null;
    }

    @Test
    public void testCalculateMonthlyPayment() {
        // Mocking user input values
        when(loanActivity.editTextLoanAmount.getText().toString()).thenReturn("10000");
        when(loanActivity.editTextDownPayment.getText().toString()).thenReturn("2000");
        when(loanActivity.editTextNumberOfPayments.getText().toString()).thenReturn("12");
        when(loanActivity.editTextInterestRate.getText().toString()).thenReturn("5");

        // Calling the method to calculate monthly payment
        loanActivity.calculateMonthlyPayment();

        // Asserting that the monthly payment is calculated correctly
        assertEquals("Monthly payment calculation is incorrect", "$ 876.97", loanActivity.textViewMonthlyPayment.getText().toString());
    }

    @Test
    public void testCalculateMonthlyPayment_WithZeroValues() {
        // Mocking user input values with zeros
        when(loanActivity.editTextLoanAmount.getText().toString()).thenReturn("0");
        when(loanActivity.editTextDownPayment.getText().toString()).thenReturn("0");
        when(loanActivity.editTextNumberOfPayments.getText().toString()).thenReturn("0");
        when(loanActivity.editTextInterestRate.getText().toString()).thenReturn("0");

        // Calling the method to calculate monthly payment
        loanActivity.calculateMonthlyPayment();

        // Asserting that the monthly payment is not calculated when input values are zero
        assertEquals("Monthly payment should not be calculated with zero values", "", loanActivity.textViewMonthlyPayment.getText().toString());
    }

    @Test
    public void testShowDownPaymentFields() {
        // Call the method to show down payment fields
        loanActivity.showDownPaymentFields();

        // Assert that the visibility of down payment fields is set to VISIBLE
        assertEquals("Down payment fields should be visible", View.VISIBLE, loanActivity.editTextDownPayment.getVisibility());
        assertEquals("Down payment fields should be visible", View.VISIBLE, loanActivity.textViewDownPayment.getVisibility());
    }
}