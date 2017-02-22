package com.cs2340.smores.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.PurityReportDBHandler;
import com.cs2340.smores.m4.model.User;
import com.cs2340.smores.m4.model.QualityReportDBHandler;
import com.cs2340.smores.m4.model.UserDBHandler;

//TODO: Only login when online (register, too)

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private HashMap<String, Integer> badLogins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model.userDBHandler = new UserDBHandler(getApplicationContext());
        Model.qualityReportDBHandler = new QualityReportDBHandler(getApplicationContext());
        Model.purityReportDBHandler = new PurityReportDBHandler(getApplicationContext());
        Model.users = Model.userDBHandler.getUsers();
        Model.qualityReports = Model.qualityReportDBHandler.getQualityReports();
        Model.purityReports = Model.purityReportDBHandler.getPurityReports();
        badLogins = new HashMap<>();

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        username.setHint("Username");
        password.setHint("Password");
    }

    @Override
    public void onRestart() {
        badLogins = new HashMap<>();
        super.onRestart();
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
        if ((user != null) && (!user.isBanned())) {
            Model.setUser(user);
            startActivity(new Intent(view.getContext(), HomeActivity.class));
        } else if (updateBadLogins(givenUsername) >= 3) {
            User bannedUser = Model.getUser(givenUsername);
            if (bannedUser != null) {
                bannedUser.ban();
            }
            new Error(view, getResources().getString(R.string.locked_account));
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

    private int updateBadLogins(String username) {
        if (Model.exists(username)) {
            badLogins.put(username, (badLogins.get(username) == null)
                    ? 1 : badLogins.get(username) + 1);
            return badLogins.get(username);
        }
        return 0;
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
