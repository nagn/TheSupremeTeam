package com.cs2340.smores.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.R;

/**
 * The class for the home page and standard activities of the app.
 */

public class HomeActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_main);

        welcome = (TextView) findViewById(R.id.welcome);
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");
    }

    @Override
    protected void onRestart() {
        welcome.setText("Welcome, " + Model.user.getRealName() + "!");
        super.onRestart();
    }


    public void onMakeReport(View view) {
        startActivity(new Intent(view.getContext(), QualityReportActivity.class));
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
