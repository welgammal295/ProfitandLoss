package com.welgammal.walid.profitandloss.Calc;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.welgammal.walid.profitandloss.R;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ExchangeActivity extends AppCompatActivity {
    private EditText dollarAmountEditText;
    private Map<String, EditText> currencyEditTextMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        dollarAmountEditText = findViewById(R.id.money_editTextDollarAmount);
        currencyEditTextMap = new HashMap<>();
        currencyEditTextMap.put("USD", findViewById(R.id.money_editTextUSD));
        currencyEditTextMap.put("MXN", findViewById(R.id.money_editTextMXN));
        currencyEditTextMap.put("EUR", findViewById(R.id.money_editTextEUR));
        currencyEditTextMap.put("JPY", findViewById(R.id.money_editTextJPY));
        currencyEditTextMap.put("KRW", findViewById(R.id.money_editTextKRW));
        currencyEditTextMap.put("CAD", findViewById(R.id.money_editTextCAD));
        currencyEditTextMap.put("GBP", findViewById(R.id.money_editTextGBP));
        currencyEditTextMap.put("AUD", findViewById(R.id.money_editTextAUD));
        currencyEditTextMap.put("CNY", findViewById(R.id.money_editTextCNY));
        currencyEditTextMap.put("CHF", findViewById(R.id.money_editTextCHF));
        currencyEditTextMap.put("HKD", findViewById(R.id.money_editTextHKD));
        currencyEditTextMap.put("INR", findViewById(R.id.money_editTextINR));

        dollarAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String dollarAmountString = editable.toString();
                if (!dollarAmountString.isEmpty()) {
                    double dollarAmount = Double.parseDouble(dollarAmountString);
                    convertToCurrencies(dollarAmount);
                } else {
                    clearCurrencyValues();
                }
            }
        });
    }
    private void convertToCurrencies(double dollarAmount) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###.##");
        for (Map.Entry<String, EditText> entry : currencyEditTextMap.entrySet()) {
            String currencyCode = entry.getKey();
            double conversionRate = getConversionRate(currencyCode);
            double convertedAmount = dollarAmount * conversionRate;
            String formattedAmount = decimalFormat.format(convertedAmount);

            String currencySymbol = "";

            switch (currencyCode) {
                case "USD":
                    currencySymbol = "$";
                    break;
                case "MXN":
                    currencySymbol = "MX$";
                    break;
                case "EUR":
                    currencySymbol = "€";
                    break;
                case "JPY":
                    currencySymbol = "¥";
                    break;
                case "KRW":
                    currencySymbol = "₩";
                    break;
                case "CAD":
                    currencySymbol = "CA$";
                    break;
                case "GBP":
                    currencySymbol = "£";
                    break;
                case "AUD":
                    currencySymbol = "A$";
                    break;
                case "CNY":
                    currencySymbol = "CN¥";
                    break;
                case "CHF":
                    currencySymbol = "CHF";
                    break;
                case "HKD":
                    currencySymbol = "HK$";
                    break;
                case "INR":
                    currencySymbol = "₹";
                    break;
                default:
                    currencySymbol = "$";
            }

            entry.getValue().setText(currencySymbol + " " + formattedAmount);
        }
    }

    private void clearCurrencyValues() {
        for (EditText editText : currencyEditTextMap.values()) {
            editText.setText("");
        }
    }

    private double getConversionRate(String currencyCode) {
        switch (currencyCode) {
            case "USD":
                return 1.0;
            case "MXN":
                return 17.423184; // Mexican Peso to USD conversion rate
            case "EUR":
                return 0.940771; // Euro to USD conversion rate
            case "JPY":
                return 154.187634; // Japanese Yen to USD conversion rate
            case "KRW":
                return 1385.682546; // South Korean Won to USD conversion rate
            case "CAD":
                return 1.378538; // Canadian Dollar to USD conversion rate
            case "GBP":
                return 0.805937; // British Pound to USD conversion rate
            case "AUD":
                return 1.566487; // Australian Dollar to USD conversion rate
            case "CNY":
                return 7.242359; // Chinese Yuan Renminbi to USD conversion rate
            case "CHF":
                return 0.907462; // Swiss Franc to USD conversion rate
            case "HKD":
                return 7.829835; // Hong Kong Dollar to USD conversion rate
            case "INR":
                return 83.631177; // Indian Rupee to USD conversion rate
            default:
                return 0.0;
        }
    }
}