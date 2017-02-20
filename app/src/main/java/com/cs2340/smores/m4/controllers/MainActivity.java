package com.cs2340.smores.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.DatabaseHandler;
import com.cs2340.smores.m4.model.Model;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model.dbHandler = new DatabaseHandler(getApplicationContext());
        Model.users = Model.dbHandler.getUsers();
    }

    /**
     * Brings the User to the login page when clicked.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onLogin(View view) {
        startActivity(new Intent(view.getContext(), LoginActivity.class));
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
