package com.cs2340.smores.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.DatabaseHandler;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.User;

//TODO: Only login when online (register, too)

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model.dbHandler = new DatabaseHandler(getApplicationContext());
        Model.users = Model.dbHandler.getUsers();

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        username.setHint("Username");
        password.setHint("Password");
    }

    /**
     * Brings the User to the login page when clicked.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onLogin(View view) {
        String givenUsername = username.getText().toString();
        String givenPassword = password.getText().toString();
        User user = Model.checkLogin(givenUsername, givenPassword);
        if (user != null) {
            Model.setUser(user);
            startActivity(new Intent(view.getContext(), HomeActivity.class));
        } else {
            new Error(view, getResources().getString(R.string.account_not_found));
        }
    }

    /**
     * Brings the User to the registration page when clicked.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onRegister(View view) {
        startActivity(new Intent(view.getContext(), RegisterActivity.class));
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
