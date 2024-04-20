package com.welgammal.walid.profitandloss;

import static java.lang.Double.parseDouble;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class LoanActivity extends AppCompatActivity {

    private Spinner spinnerLoanType;
    private EditText editTextLoanAmount, editTextDownPayment, editTextNumberOfPayments, editTextInterestRate;
    private TextView textViewDownPayment, textViewMonthlyPayment;
    private Button buttonCalculate;

    private double defaultInterestRate;
    private int defaultNumberOfPayments;
    private boolean loanTypeSelected = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);


        spinnerLoanType = findViewById(R.id.spinnerLoanType);
        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextDownPayment = findViewById(R.id.editTextDownPayment);
        editTextNumberOfPayments = findViewById(R.id.editTextNumberOfPayments);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        textViewDownPayment = findViewById(R.id.textViewDownPayment);
        textViewMonthlyPayment = findViewById(R.id.textViewMonthlyPayment);
        buttonCalculate = findViewById(R.id.buttonCalculate);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.loan_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoanType.setAdapter(adapter);

        spinnerLoanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedLoanType = spinnerLoanType.getSelectedItem().toString();
                if(selectedLoanType.equals("Personal") || selectedLoanType.equals("School")) {
                    hideDownPaymentFields();
                } else {
                    showDownPaymentFields();
                }
                resetFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMonthlyPayment();
            }
        });
    }

    private void calculateMonthlyPayment() {
        double loanAmount = parseDouble(editTextLoanAmount.getText().toString());
        double downPayment = parseDouble(editTextDownPayment.getText().toString());
        int numberOfPayments = parseInt(editTextNumberOfPayments.getText().toString());
        double interestRate = parseDouble(editTextInterestRate.getText().toString());

        if (loanAmount == 0 || numberOfPayments == 0 || interestRate == 0) {
            Toast.makeText(this, "Please fill all fields with valid values", Toast.LENGTH_SHORT).show();
            return;
        }

        setLoanAmount(loanAmount);

        if (isDownPaymentVisible()) {
            loanAmount -= downPayment;
        }

        // Calculate monthly payment
        double monthlyInterestRate = interestRate / 100 / 12;
        double monthlyPayment = loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);

        // Display monthly payment
        textViewMonthlyPayment.setText(String.format("%.2f", monthlyPayment));
        setMonthlyPayment(monthlyPayment);
        setDownPayment(downPayment);

    }

    private void hideDownPaymentFields() {
        editTextDownPayment.setVisibility(View.GONE);
        textViewDownPayment.setVisibility(View.GONE);
    }

    private void showDownPaymentFields() {
        editTextDownPayment.setVisibility(View.VISIBLE);
        textViewDownPayment.setVisibility(View.VISIBLE);
    }

    private boolean isDownPaymentVisible() {
        return editTextDownPayment.getVisibility() == View.VISIBLE;
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private void setLoanAmount(double amount) {
        String formattedAmount = decimalFormat.format(amount);
        editTextLoanAmount.setText("$ " + formattedAmount);
    }

    // Method to set down payment value with proper comma formatting
    private void setDownPayment(double amount) {
        String formattedAmount = decimalFormat.format(amount);
        editTextDownPayment.setText("$ " + formattedAmount);
    }

    // Method to set monthly payment value with proper comma formatting
    private void setMonthlyPayment(double amount) {
        String formattedAmount = decimalFormat.format(amount);
        textViewMonthlyPayment.setText("$ " + formattedAmount);
    }
    private void resetFields() {
        editTextLoanAmount.setText("");
        editTextDownPayment.setText("");
        editTextNumberOfPayments.setText("");
        editTextInterestRate.setText("");
        textViewMonthlyPayment.setText("");
    }
}