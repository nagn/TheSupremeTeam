package com.cs2340.smores.m4;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
