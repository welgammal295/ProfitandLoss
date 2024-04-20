package com.welgammal.walid.profitandloss;

import static com.welgammal.walid.profitandloss.MainActivity.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.welgammal.walid.profitandloss.databinding.ActivityDiscountBinding;

import java.util.Locale;

public class DiscountActivity extends AppCompatActivity {

    private ActivityDiscountBinding binding;

    double mPrice = 0.0;
    double mDiscount = 0.0;
    double mCountUnit = 0.0;
    double fullPrice = 0.0;
    int userId;
    double totalDiscount = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityDiscountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.discountDisplayTextView.setOnClickListener(new View.OnClickListener() {
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

        binding.discountCalcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDiscount();
                updateDisplay();
            }
        });



    }

        private void updateDisplay(){
            double totalDiscount = calcDiscount();
            double fullPrice = calcFullPrice();
            String disDisplay = String.format(Locale.US, "%s$%.2f", fullPrice < 0 ? "-" : "+", Math.abs(fullPrice));
            String fullPriceDisplay = String.format(Locale.US, "%s$%.2f", totalDiscount < 0 ? "-" : "+", Math.abs(totalDiscount));

            binding.fullPriceOutputTextView.setText(disDisplay);
            binding.discountCalcOutputTextView.setText(fullPriceDisplay);



    }

    private double calcDiscount() {
        fullPrice = calcFullPrice();
        try {
            mDiscount =Double.parseDouble(binding.discountValueInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Operating Expenses edit text. ");
        }
        return fullPrice * (mDiscount / 100.00);
    }

    private double calcFullPrice() {

        try {
            mPrice =Double.parseDouble(binding.priceInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Operating Expenses edit text. ");
        }
        try{

            mCountUnit =Double.parseDouble(binding.unitsCountInputEditText.getText().toString());
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from Operating Expenses edit text. ");
        }
        return mPrice * mCountUnit;
    }
}