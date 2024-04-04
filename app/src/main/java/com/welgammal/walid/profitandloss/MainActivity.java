package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.Calc.Calculations;
import com.welgammal.walid.profitandloss.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    double mRevenue = 0.0;
    double mCostOfSale = 0.0;
    double mGrossProfit = 0.0;
    double totalOperatingExpenses = 0.0;
    double mOperatingExpenses = 0.0;
    double operatingIncome =0.0;
    double mOtherExpenses = 0.0;
    double mOtherIncomes = 0.0;
    String mYear = "2024";
    String mMonth = "January";

    public static final String TAG = "DAC_PROFITLOSS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.netIncomesOnputTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainMenu.mainMenuFactory(getApplicationContext());
                startActivity(intent);
            }
        });

                binding.netIncomeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateDisplay();
                    }
                });
    }
    private void updateDisplay(){
        //String currentInfo = binding.netIncomesOnputTextView.toString();
        double netIncome = calculateNetIncome();
        String newDispay = String.format(Locale.US,"$%.2f", netIncome);
        binding.netIncomesOnputTextView.setText(newDispay);
    }

    private double calculateNetIncome(){
        try {
            mRevenue =Double.parseDouble(binding.revenueInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Revenue edit text. ");
        }

        try {
            mCostOfSale =Double.parseDouble(binding.costOfSalesInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Cost of Sales edit text. ");
        }
        try {
            mOperatingExpenses =Double.parseDouble(binding.operatingExpensesInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Operating Expenses edit text. ");
        }

        try {
            mOtherExpenses=Double.parseDouble(binding.otherExpensesInputEditText.getText().toString());

        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Other Expenses edit text. ");
        }
        try {

            mOtherIncomes =Double.parseDouble(binding.otherIncomesInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Other Incomes edit text. ");
        }
        mGrossProfit = Calculations.grossProfit(mRevenue, mCostOfSale);
        totalOperatingExpenses = Calculations.totalOperatingExpenses(mOperatingExpenses, mOtherExpenses);
        operatingIncome = Calculations.operatingIncome(mGrossProfit, totalOperatingExpenses);

        return Calculations.netIncome(mOtherIncomes, operatingIncome);
    }


}