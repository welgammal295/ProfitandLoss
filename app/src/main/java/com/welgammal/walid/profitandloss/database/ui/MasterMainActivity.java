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

import com.welgammal.walid.profitandloss.AccountsActivity;
import com.welgammal.walid.profitandloss.Calc.ExchangeActivity;
import com.welgammal.walid.profitandloss.MainMenu;
import com.welgammal.walid.profitandloss.R;
import com.welgammal.walid.profitandloss.ReportActivity;

public class MasterMainActivity extends AppCompatActivity implements View.OnClickListener{


    public static Intent masterMenuFactory(Context context, int userId){
        Intent intent = new Intent(context, MainMenu.class);
        //intent.putExtra(MASTER_MENU_ACTIVITY_USER_ID, userId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_main);

        Button accountsButton = findViewById(R.id.accountsButton);
        Button reportButton = findViewById(R.id.reportButton);
        Button exchangeButton = findViewById(R.id.exchangeButton);
        Button profitCalcButton = findViewById(R.id.ProfitCalcButton);

        accountsButton.setOnClickListener((View.OnClickListener) this);
        reportButton.setOnClickListener((View.OnClickListener) this);
        exchangeButton.setOnClickListener((View.OnClickListener) this);
        profitCalcButton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.accountsButton) {
            intent = new Intent(this, AccountsActivity.class);
        } else if (v.getId() == R.id.reportButton) {
            intent = new Intent(this, ReportActivity.class);
        } else if (v.getId() == R.id.exchangeButton) {
            intent = new Intent(this, ExchangeActivity.class);
        } else if (v.getId() == R.id.ProfitCalcButton) {
            intent = new Intent(this, MainMenu.class);
        } else {
            return;
        }
        startActivity(intent);
    }
}