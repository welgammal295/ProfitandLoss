package com.welgammal.walid.profitandloss.database.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.welgammal.walid.profitandloss.LoginActivity;
import com.welgammal.walid.profitandloss.MainMenu;
import com.welgammal.walid.profitandloss.R;
import com.welgammal.walid.profitandloss.database.ProfitLossDB;
import com.welgammal.walid.profitandloss.database.ProfitLossRepository;
import com.welgammal.walid.profitandloss.database.UserDAO;
import com.welgammal.walid.profitandloss.database.entities.User;
import com.welgammal.walid.profitandloss.databinding.ActivityUserSignupBinding;

public class UserSignup extends AppCompatActivity {
    private ActivityUserSignupBinding binding;
    private ProfitLossRepository repository;
    String username = "";
    String password = "";
    public static final String TAG = "DAC_PROFITLOSS";
    private UserDAO userDAO;

    public static Intent userSignFactory(Context context) {
        Intent intent = new Intent(context, UserSignup.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        binding = ActivityUserSignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        repository = ProfitLossRepository.getRepository(getApplication());  /** get instance of repository */

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());

                startActivity(intent);

            }


        });
    }

    /** Insert records in database */
    private void insertUserRecord(){
        User user = new User(username, password);
       // repository.insertUser(user);

    }
//    public void insertUserRecord(User...user) {
//        ProfitLossDB.databaseWriteExecutor.execute(() ->
//        {
//            userDAO.insert(user);
//        });
//    }

    private void getUserRecord() {

        try {
            username =binding.createUserNameEditText.getText().toString();
        }catch(NumberFormatException e){
            Log.d(TAG, "Error reading value from username text. ");
        }
            try {
                password =binding.createPasswordEditText.getText().toString();
            }catch(NumberFormatException e){
                Log.d(TAG, "Error reading value from password text. ");
            }
            if (username == null || password == null){
                Toast.makeText(getApplicationContext(), " Please enter a username and password", Toast.LENGTH_LONG).show();
            }
            else {


    }

    }
}