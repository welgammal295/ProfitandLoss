package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.welgammal.walid.profitandloss.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        usernameEditText = findViewById(R.id.userNameLoginEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        Button loginButton = findViewById(R.id.loginButton);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //variables to save values
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Retrieve stored values
            String storedUsername = sharedPreferences.getString("username", "");
            String storedPassword = sharedPreferences.getString("password", "");

            // Check if entered values match stored values
            if (username.equals(storedUsername) && password.equals(storedPassword)) {
                // Login successful, navigate to main menu
                Intent intent = MainMenu.mainMenuFactory(LoginActivity.this, 0);
                startActivity(intent);
                finish();
            } else {
                // Login failed, display error message
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }
    });
}

    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);

    }

}