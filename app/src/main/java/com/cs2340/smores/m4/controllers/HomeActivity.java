package com.cs2340.smores.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cs2340.smores.m4.model.Admin;
import com.cs2340.smores.m4.model.Manager;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.User;
import com.cs2340.smores.m4.model.Worker;

/**
 * The class for the home page and standard activities of the app.
 */

public class HomeActivity extends AppCompatActivity {

    TextView welcome;
    TextView accountType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");

        accountType = (TextView) findViewById(R.id.accountType);
        accountType.setText("You are " + getType() + ".");
    }

    @Override
    protected void onRestart() {
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");
        accountType.setText("You are " + getType() + ".");
        super.onRestart();
    }

    public String getType() {
        if (Model.user instanceof Admin) { return "an Admin"; }
        if (Model.user instanceof Manager) { return "a Manager"; }
        if (Model.user instanceof Worker) { return "a Worker"; }
        return "a Customer";
    }

    public void onEditAccount(View view) {
        startActivity(new Intent(view.getContext(), EditAccountActivity.class));
    }

    /**
     * Returns the User to the main page of the app and logs them out.
     *
     * @param view Default view parameter for onClick custom method.
     */
    public void onLogoutPressed(View view) {
        Model.setUser(null);
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
