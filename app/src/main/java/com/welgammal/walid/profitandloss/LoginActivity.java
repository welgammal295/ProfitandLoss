package com.welgammal.walid.profitandloss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.database.ProfitLossRepository;
import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.database.ui.UserSignup;
import com.welgammal.walid.profitandloss.databinding.ActivityLoginBinding;

import java.security.PrivateKey;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private ProfitLossRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        repository = ProfitLossRepository.getRepository(getApplication());
        binding.createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserSignup.userSignFactory(getApplicationContext());

                startActivity(intent);
            }
        });
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verifyUser();
            }
        });
    }

    private void verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        if (username.isEmpty()) {
            toastMaker("Username may not be blank.");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
            if (user != null){
                String password = binding.passwordLoginEditText.getText().toString();
                if (password.equals(user.getPassword())) {
                    startActivity(MainMenu.mainMenuFactory(getApplicationContext(), user.getId()));
                }else {
                    toastMaker("Invalid password");
                    binding.passwordLoginEditText.setSelection(0);
                }
            }else {
                toastMaker(String.format("No user %s is not a valid username", username));
                binding.userNameLoginEditText.setSelection(0);
            }

        });
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);

    }

}