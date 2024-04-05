package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.databinding.ActivityMainBinding;
import com.welgammal.walid.profitandloss.databinding.ActivityMainMenuBinding;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private ActivityMainMenuBinding binding;
    static String year = "2024";
    static String month = "January";

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

        Spinner spinner = findViewById(R.id.years);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainMenu.this, "Please selected a year and a month ", Toast.LENGTH_SHORT).show();
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
    }
/** TODO: Pass on year and month to Main activity */
    public static Intent mainMenuFactory(Context context) {
        Intent intent = new Intent(context, MainMenu.class);
        //intent.putExtra("data", String.valueOf(spinner.getSelectedItem()));
        return intent;
    }


    }
