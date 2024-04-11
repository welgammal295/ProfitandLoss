package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.database.ProfitLossRepository;
import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.databinding.ActivityLoginBinding;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private ProfitLossRepository repository;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        repository = ProfitLossRepository.getRepository(getApplication());
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verifyUser()) {
                    toastMaker("Invalid credentials");

                } else {
                    Intent intent = MainMenu.mainMenuFactory(getApplicationContext(), user.getId());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        if (username.isEmpty()) {
            toastMaker("Username may not be blank.");
            return false;
        }
        user = repository.getUserByUserName(username);
        if (user != null) {
            String password = binding.passwordLoginEditText.getText().toString();
            if (password.equals(user.getPassword())) {
                return true;
            } else{
                toastMaker("Invalid password.");
                return false;
            }
        }
        toastMaker(String.format("No %s found"
         , username));
        return false;
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);

    }

}