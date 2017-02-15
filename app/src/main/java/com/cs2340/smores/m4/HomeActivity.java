package com.cs2340.smores.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * The class for the home page and standard activities of the app.
 */

public class HomeActivity extends AppCompatActivity {

    TextView welcome;
    TextView accountType;
    Model model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);
        finishActivity(224);

        model = Model.getInstance();
        User user = model.getUser();

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome, " + user.getRealName() + "!");

        String type;
        if (user.isAdmin()) {
            type = "an Admin";
        } else if (user.isManager()) {
            type = "a Manager";
        } else if (user.isWorker()) {
            type = "a Worker";
        } else {
            type = "a Customer";
        }

        accountType = (TextView) findViewById(R.id.accountType);
        accountType.setText("You are " + type + ".");
    }

    /**
     * Returns the User to the main page of the app and logs them out.
     *
     * @param view Default view parameter for onClick custom method.
     */
    public void onLogoutPressed(View view) {
        model.setUser(null);
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
