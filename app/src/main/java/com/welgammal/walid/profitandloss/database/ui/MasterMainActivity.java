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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.welgammal.walid.profitandloss.databinding.ActivityMasterMainBinding;

import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.LoanActivity;
import com.welgammal.walid.profitandloss.Calc.ExchangeActivity;
import com.welgammal.walid.profitandloss.MainMenu;
import com.welgammal.walid.profitandloss.R;
import com.welgammal.walid.profitandloss.ReportActivity;


public class MasterMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMasterMainBinding binding;

    private static final String MAIN_MENU_ACTIVITY_USER_ID = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_USER_ID" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_master_main);
        binding = ActivityMasterMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int userId = getIntent().getIntExtra("userId", -1);

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

        // Pass user ID to other activities
        accountsButton.setTag(userId);
        reportButton.setTag(userId);
        exchangeButton.setTag(userId);
        profitCalcButton.setTag(userId);

        //app menu
        createAndPrepareMenu();
    }

    @Override
    public void onClick(View v) {
        int userId = (int) v.getTag();
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

    public static Intent masterMenuFactory(Context context, int userId){
        Intent intent = new Intent(context, MainMenu.class);
        intent.putExtra(MAIN_MENU_ACTIVITY_USER_ID, userId);
        return intent;
    }

    // Create and prepare the menu
    private void createAndPrepareMenu() {
        MenuInflater inflater = getMenuInflater();
        Menu menu = binding.toolbar.getMenu();

        // Call method from AppMenu to create the menu
        AppMenu.createMenu(inflater, menu);

        // Prepare the menu
        AppMenu.prepareMenu(menu, new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle logout menu item click
                AppMenu.showLogoutDialog(MasterMainActivity.this);
                return true;
            }
        }, "Username");
    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    // Handle action bar item clicks here.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle other menu items in your activity
        switch (item.getItemId()) {
            // Handle other menu items if needed
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}