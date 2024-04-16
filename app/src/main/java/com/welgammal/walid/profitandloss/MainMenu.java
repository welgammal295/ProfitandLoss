package com.welgammal.walid.profitandloss;

import static com.welgammal.walid.profitandloss.MainActivity.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.database.ProfitLossRepository;
import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.databinding.ActivityMainMenuBinding;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private static final String MAIN_MENU_ACTIVITY_USER_ID = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_USER_ID" ;
    private static final String MAIN_MENU_ACTIVITY_YEAR = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_YEAR" ;
    private static final String MAIN_MENU_ACTIVITY_MONTH = "com.welgammal.walid.profitandloss.MAIN_MENU_ACTIVITY_MONTH" ;
    static final String SHARED_PREFERENCE_USERID_KEY = "com.welgammal.walid.profitandloss.SHARED_PREFERENCE_USERID_KEY" ;
    private static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.welgammal.walid.profitandloss.SAVED_INSTANCE_STATE_USERID_KEY" ;

    private static final int LOGGED_OUT = -1;
    private ActivityMainMenuBinding binding;
    public ProfitLossRepository repository;
    static String year = "2024";
    static String month = "January";
    protected static int loggedInUserId = -1;
    private User user;
    public Button taxRateButton;
    private TextView selectTaxRateTextView;
    private EditText taxRateEditText;

    double userTaxRate = 8.85;

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

    private void saveTaxRate(String year, float userTaxRate) {
        SharedPreferences sharedPref = getSharedPreferences("TaxRates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(year, userTaxRate);
        editor.apply();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        repository = ProfitLossRepository.getRepository(getApplication());
        loginUser(savedInstanceState);

        // User still not logged in - go to login screen
        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        updateSharedPreference();

        Spinner spinner = findViewById(R.id.years);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(MainMenu.this, "Please selected a year and a month ", Toast.LENGTH_SHORT).show();

                String selectedYear = spinner.getSelectedItem().toString();
                SharedPreferences sharedPref = getSharedPreferences("TaxRates", Context.MODE_PRIVATE);
                float savedTaxRate = sharedPref.getFloat(selectedYear, (float) userTaxRate);
                taxRateEditText.setText(String.valueOf(savedTaxRate));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> yearsList = new ArrayList<>(); //initialized as a class level variable, needed for tax rate to link to year
        yearsList.add("2024");
        yearsList.add("2025");
        yearsList.add("2026");
        yearsList.add("2027");
        yearsList.add("2028");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);



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


        /** Pass on year, month, and userId to Main activity
         *
         * */
        //Checks if user entered a tax rate for x year
        SharedPreferences sharedPref = getSharedPreferences("TaxRates", Context.MODE_PRIVATE);
        float savedTaxRate = sharedPref.getFloat(year, (float) userTaxRate);

        // Initialize taxRateEditText
        taxRateEditText = findViewById(R.id.taxRateEditText);


        // Set the tax rate EditText to the last saved tax rate
        taxRateEditText.setText(String.valueOf(savedTaxRate));


        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = spinner.getSelectedItem().toString();
                month = spinnerM.getSelectedItem().toString();
                Intent intent = MainActivity.mainActivityFactory(MainMenu.this, loggedInUserId, year, month);
                startActivity(intent);
            }
        });


        binding.setTaxRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = taxRateEditText.getText().toString();

                try {
                    userTaxRate = Double.parseDouble(userInput);
                    String selectedYear = spinner.getSelectedItem().toString();
                    saveTaxRate(selectedYear, (float) userTaxRate);
                }catch (NumberFormatException e){
                    Log.e(TAG, "Please enter a valid number");
                }

                Intent intent = MainActivity.mainActivityFactory(MainMenu.this, loggedInUserId, year, month);
                intent.putExtra("userTaxRate", userTaxRate);
                startActivity(intent); // Starts MainActivity, only want to send tax rate
            }
        });

    }


    private void loginUser(Bundle savedInstanceState) {
        // check shared preference for logged in user
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.sharedprefrence_file_key),
                Context.MODE_PRIVATE);

        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);

        if (loggedInUserId == LOGGED_OUT & savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)){
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT){
            loggedInUserId = getIntent().getIntExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if (loggedInUserId == LOGGED_OUT) {
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if (this.user != null) {
                invalidateOptionsMenu();
            }
            if (this.user.isAdmin()){
                invalidateOptionsMenu();
                //Tax Rate Button
                taxRateButton = findViewById(R.id.setTaxRate);
                taxRateButton.setVisibility(View.VISIBLE);
                //Tax Rate EditText
                /*taxRateEditText = findViewById(R.id.taxRateEditText);*/
                taxRateEditText.setVisibility(View.VISIBLE);
                //Tax Rate TextView
                selectTaxRateTextView = findViewById(R.id.selectTaxRateTextView);
                selectTaxRateTextView.setVisibility(View.VISIBLE);
            }

        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){

        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
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
        if (user == null) {
            return false;
        }
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

        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        getIntent().putExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }
    private void updateSharedPreference(){

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.sharedprefrence_file_key)
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();

        getIntent().putExtra(MAIN_MENU_ACTIVITY_USER_ID, LOGGED_OUT);

    }

        static Intent mainMenuFactory (Context context,int userId){
            Intent intent = new Intent(context, MainMenu.class);
            intent.putExtra(MAIN_MENU_ACTIVITY_USER_ID, userId);
            return intent;
        }


    }
