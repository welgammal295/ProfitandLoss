package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.welgammal.walid.profitandloss.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    double mRevenue = 0.0;
    double mCostofSale = 0.0;
    double mGrossProfit = 0.0;
    double mOperatingExpenses = 0.0;
    double mOtherExpenses = 0.0;
    double mOtherIncomes = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.netIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();

            }
        });
    }
    private void getInformationFromDisplay(){

    }
}