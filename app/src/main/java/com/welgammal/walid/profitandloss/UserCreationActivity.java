package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserCreationActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        usernameEditText = findViewById(R.id.userNameLoginEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        Button userCreationButton = findViewById(R.id.continueButton);

        userCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if username already exists
                if (sharedPreferences.contains(username)) {
                    Toast.makeText(UserCreationActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // Save username and password to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username, password);
                    editor.apply();

                    Toast.makeText(UserCreationActivity.this, "User creation successful", Toast.LENGTH_SHORT).show();
                    finish(); // Finish UserCreationActivity
                }
            }
        });
    }
}