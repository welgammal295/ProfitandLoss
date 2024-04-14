package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.Calc.Calculations;
import com.welgammal.walid.profitandloss.database.ProfitLossRepository;
import com.welgammal.walid.profitandloss.database.entities.Elements;
import com.welgammal.walid.profitandloss.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
/** Get instance of database */

    private ProfitLossRepository repository;
    private static final String MAIN_MENU_ACTIVITY_YEAR = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_YEAR" ;
    private static final String MAIN_MENU_ACTIVITY_MONTH = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_MONTH" ;
    double mRevenue = 0.0;
    double mCostOfSale = 0.0;
    double mGrossProfit = 0.0;
    double totalOperatingExpenses = 0.0;
    double mOperatingExpenses = 0.0;
    double operatingIncome =0.0;
    double mOtherExpenses = 0.0;
    double mOtherIncomes = 0.0;
    int userId;
    public static final String TAG = "DAC_PROFITLOSS";
    String mYear;
    String mMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        repository = ProfitLossRepository.getRepository(getApplication());  /** get instance of repository */
        binding.netIncomesOutputTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainMenu.mainMenuFactory(getApplicationContext(), userId);

                startActivity(intent);
            }
        });

                binding.netIncomeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertElementRecord();
                        updateDisplay();
                    }
                });
    }

    public static Intent mainActivityFactory(Context context, int loggedInUserId, String mYear, String month) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("MAIN_MENU_ACTIVITY_YEAR", mYear);
        return intent;

    }


    /** Insert records in database */
    private void insertElementRecord(){
        Elements element = new Elements(mRevenue, mCostOfSale, mOperatingExpenses,
        mOtherExpenses, mOtherIncomes, MainMenu.year, MainMenu.month, MainMenu.loggedInUserId);
        repository.insertElements(element);

    }
    private void updateDisplay(){
        double netIncome = calculateNetIncome();
        double grossProfit = calculateGrossProfit();
        String grossDisplay = String.format(Locale.US, "%s$%.2f", grossProfit < 0 ? "-" : "+", Math.abs(grossProfit));
        String netDisplay = String.format(Locale.US, "%s$%.2f", netIncome < 0 ? "-" : "+", Math.abs(netIncome));

        binding.grossProfitOutputTextView.setText(grossDisplay);
        binding.netIncomesOutputTextView.setText(netDisplay);
        Log.i(TAG, repository.getAllLogs().toString());
    }
        /* TODO: How to get the year, month, taxrate */
    private double calculateNetIncome(){

        calculateGrossProfit();
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

    private double calculateGrossProfit() {
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
        return mRevenue - mCostOfSale;
    }


}