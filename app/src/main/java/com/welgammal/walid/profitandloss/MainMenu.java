package com.welgammal.walid.profitandloss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.databinding.ActivityMainBinding;
import com.welgammal.walid.profitandloss.databinding.ActivityMainMenuBinding;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private static final String MAIN_MENU_ACTIVITY_USER_ID = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_USER_ID" ;
    static final String SHARED_PREFERENCE_USERID_KEY = "com.welgammal.walid.profitandloss.SHARED_PREFERENCE_USERID_KEY" ;
    private static final String SHARED_PREFERENCE_USERID_VALUE = "com.welgammal.walid.profitandloss.SHARED_PREFERENCE_USERID_VALUE" ;
    private static final int LOGGED_OUT = -1;
    private ActivityMainMenuBinding binding;
    static String year = "2024";
    static String month = "January";

    protected static int loggedInUserId = -1;
    private User user;

    public static String getYear() {
        return year;
    }

    public void setYear(String year) {
        year = year;
    }

    public static String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        month = month;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        loginUser();
        
        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        invalidateOptionsMenu();


        Spinner spinner = findViewById(R.id.years);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(MainMenu.this, "Please selected a year and a month ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> yearsList = new ArrayList<>();
        yearsList.add("2024");
        yearsList.add("2025");
        yearsList.add("2026");
        yearsList.add("2027");
        yearsList.add("2028");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);
        year = spinner.getSelectedItem().toString();


        Spinner spinnerM = findViewById(R.id.months);

        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> monthsList = new ArrayList<>();
        monthsList.add("January");
        monthsList.add("February");
        monthsList.add("March");
        monthsList.add("April");
        monthsList.add("May");
        monthsList.add("June");
        monthsList.add("July");
        monthsList.add("August");
        monthsList.add("September");
        monthsList.add("October");
        monthsList.add("November");
        monthsList.add("December");

        ArrayAdapter<String> adapterM = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthsList);
        adapterM.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerM.setAdapter(adapterM);
        month = spinnerM.getSelectedItem().toString();
        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.mainActivityFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }


    private void loginUser() {
        // check shared preference for logged in user
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,
                Context.MODE_PRIVATE);
        loggedInUserId = sharedPreferences.getInt(SHARED_PREFERENCE_USERID_VALUE, LOGGED_OUT);
        if(loggedInUserId != LOGGED_OUT){
            return;
        }
        // check intent for logged in user
        loggedInUserId = getIntent().getIntExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        item.setTitle(user.getUsername());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {

                showLogoutDialog();
                return false;
            }
        });

        return true;
    }
    private void showLogoutDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainMenu.this);
        final AlertDialog alertDialog = alertBuilder.create();
        alertBuilder.setMessage("Are you sure you want to log out?");
        alertBuilder.setPositiveButton("Log out?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    private void logout() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(SHARED_PREFERENCE_USERID_KEY, LOGGED_OUT);
        sharedPrefEditor.apply();

        getIntent().putExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);

        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));


        /** TODO: Pass on year, month, and userId to Main activity
         * hint: use putExtra to pass userID, year and month
         * */
    }
        static Intent mainMenuFactory (Context context,int userId){
            Intent intent = new Intent(context, MainMenu.class);
            intent.putExtra(MAIN_MENU_ACTIVITY_USER_ID, userId);
            return intent;
        }

    }
