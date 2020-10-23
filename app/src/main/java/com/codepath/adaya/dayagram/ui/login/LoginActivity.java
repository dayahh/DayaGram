package com.codepath.adaya.dayagram.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.adaya.dayagram.MainActivity;
import com.codepath.adaya.dayagram.R;
import com.codepath.adaya.dayagram.ui.login.LoginViewModel;
import com.codepath.adaya.dayagram.ui.login.LoginViewModelFactory;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/*
 * A login screen that offers login via username/password
 */
public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button buttonLogin;

    //checks if user has previously logged on so they can go straight to main activity
    if (ParseUser.getCurrentUser() != null){
        goMainActivity();
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "login button clicked!");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(username, password);
            }
        });
    }

    //This is where we let our user login in depending on if their login credentials are correct
    private void loginUser(String username, String password) {
        Log.i(TAG, "attempting to log in user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            //checking if logging in was an issue and if so then it has an error that we check for
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with login bruh", e);
                    //TODO: Add toast saying issue with login so user can know the error too
                    return;
                }
                //naviagtes to main activity if the user successfully signs in properly
                goMainActivity();
                //Toasts visually show the user that they were able to log in with message @ bottom of screen
                Toast.makeText(LoginActivity.this, "Hacker voice: I'm in", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //entering the main activity page
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        //removes the chance to press the back button and go into the log in page again
        finish();
    }

}