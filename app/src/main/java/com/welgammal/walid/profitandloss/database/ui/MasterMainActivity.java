package com.welgammal.walid.profitandloss.database.ui;



import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.welgammal.walid.profitandloss.LoanActivity;
import com.welgammal.walid.profitandloss.Calc.ExchangeActivity;
import com.welgammal.walid.profitandloss.MainMenu;
import com.welgammal.walid.profitandloss.R;
import com.welgammal.walid.profitandloss.ReportActivity;

public class MasterMainActivity extends AppCompatActivity implements View.OnClickListener {

    // Factory method to create an intent for MainMenu activity
    public static Intent masterMenuFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainMenu.class);
        intent.putExtra("userId", userId); // Pass the user ID as an extra
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_main);

        // Find buttons
        Button accountsButton = findViewById(R.id.loanButton);
        Button reportButton = findViewById(R.id.reportButton);
        Button exchangeButton = findViewById(R.id.exchangeButton);
        Button profitCalcButton = findViewById(R.id.ProfitCalcButton);

        // Set click listeners
        accountsButton.setOnClickListener(this);
        reportButton.setOnClickListener(this);
        exchangeButton.setOnClickListener(this);
        profitCalcButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.loanButton) {
            intent = new Intent(this, LoanActivity.class);
        } else if (v.getId() == R.id.reportButton) {
            intent = new Intent(this, ReportActivity.class);
        } else if (v.getId() == R.id.exchangeButton) {
            intent = new Intent(this, ExchangeActivity.class);
        } else if (v.getId() == R.id.ProfitCalcButton) {
            intent = masterMenuFactory(this, getIntent().getIntExtra("userId", -1));
        } else {
            return;
        }
        startActivity(intent);
    }
}