package com.cs2340.smores.m4;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * The activity class for the login page and process of the app.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        username = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        model = Model.getInstance();
    }

    /**
     * The custom login button handler.  Checks if the User has proper login information.
     * If the info is correct, it logs them in and brings them to the home page.
     * If it isn't, they are presented with an appropriate error message.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onLoginPressed(View view) {
        String givenUsername = username.getText().toString();
        String givenPassword = password.getText().toString();
        User user = Model.checkLogin(givenUsername, givenPassword);
        if (user != null) {
            model.setUser(user);
            startActivity(new Intent(view.getContext(), HomeActivity.class));
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setView(R.layout.popup);
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            TextView txtView = (TextView) alertDialog.findViewById(R.id.txtView);
            Resources res = getResources();
            String error_message = res.getString(R.string.account_not_found);
            if (txtView != null) {
                txtView.setText(error_message);
            }

            Button btn = (Button) alertDialog.findViewById(R.id.close_popup);
            if (btn != null) {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        }
    }

    /**
     * Returns the user to the main page of the app and cancels the login process.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onCancelPressed(View view) {
        super.onBackPressed();
    }

}
