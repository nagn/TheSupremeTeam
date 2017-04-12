package com.cs2340.smores.m4.controllers;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.LogDBHandler;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.PurityReportDBHandler;
import com.cs2340.smores.m4.model.SourceReportDBHandler;
import com.cs2340.smores.m4.model.User;
import com.cs2340.smores.m4.model.UserDBHandler;

/**
 * Both the welcome page and the login screen of the App.
 * Registration is also available.
 *
 * @author Team 48
 */

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private HashMap<String, Integer> badLogins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Model.userDBHandler = new UserDBHandler(getApplicationContext());
        Model.sourceReportDBHandler = new SourceReportDBHandler(
                getApplicationContext());
        Model.purityReportDBHandler = new PurityReportDBHandler(
                getApplicationContext());
        Model.logDBHandler = new LogDBHandler(getApplicationContext());
        Model.users = Model.userDBHandler.getUsers();
        Model.sourceReports = Model.sourceReportDBHandler.getQualityReports();
        Model.purityReports = Model.purityReportDBHandler.getPurityReports();
        Model.log = Model.logDBHandler.getLog();
        badLogins = new HashMap<>();

        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);

        username.setHint("Username");
        password.setHint("Password");
    }

    @Override
    public void onRestart() {
        badLogins = new HashMap<>();
        super.onRestart();
    }

    /**
     * Attempts to log the User into the app when the Login Button is pressed.
     * If the given username exist and a login has been attempted at least 3
     * times during the current login session, the User associated with the
     * given username is locked out of login until an Admin returns the
     * privilege to them.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onLogin(View view) {
        String givenUsername = username.getText().toString();
        String givenPassword = password.getText().toString();

        User user = Model.checkLogin(givenUsername, givenPassword);
        if ((user != null) && (!user.isLocked())) {
            Model.setUser(user);
            startActivity(new Intent(view.getContext(), HomeActivity.class));
            username.setText("");
            password.setText("");
            finish();
        } else if ((givenUsername.length() == 0)
                || (givenPassword.length() == 0)) {
            new Error(view, getString(R.string.missing_info_login));
        } else if (updateBadLogins(givenUsername) >= 3) {
            User lockedUser = Model.getUser(givenUsername);
            if (lockedUser != null) {
                lockedUser.lockOut();
            }
            new Error(view, getString(R.string.locked_account));
        } else {
            new Error(view, getString(R.string.account_not_found));
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

    /**
     * Method to update the HashMap of usernames to keep bad login info.
     *
     * @param username The username being used as login info.
     * @return The number of logins attempted with the given username if
     * it exists in the system.
     */
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
