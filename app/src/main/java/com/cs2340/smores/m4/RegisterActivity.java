package com.cs2340.smores.m4;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * The activity class for the registration page and process of the app.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText editRealName;
    private EditText editUsername;
    private EditText editPassword1;
    private EditText editPassword2;
    private Model model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        editRealName = (EditText) this.findViewById(R.id.editRealName);
        editUsername = (EditText) this.findViewById(R.id.editUsername);
        editPassword1 = (EditText) this.findViewById(R.id.editPassword1);
        editPassword2 = (EditText) this.findViewById(R.id.editPassword2);

        model = Model.getInstance();
    }

    /**
     * The custom button handler for the registration button.
     * If all info is present and passwords match, the account is created and the User is returned
     * to the main page of the app.  If not, an appropriate error message is presented.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onRegister(View view) {
        String realName = editRealName.getText().toString();
        String username = editUsername.getText().toString();
        String password1 = editPassword1.getText().toString();
        String password2 = editPassword2.getText().toString();

        if (!(password1.equals(password2))
                || (realName.length() == 0) || (username.length() == 0)
                || (password1.length() == 0) || (password2.length() == 0) ||
                !isValid(username) || !isNew(username)) {
            Resources res = getResources();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
            alertDialogBuilder.setView(R.layout.popup);
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            String error_message;
            if (!isNew(username)) {
                error_message = res.getString(R.string.missing_info);
            } else if (!isValid(username)) {
                error_message = res.getString(R.string.missing_info);
            } else if (password1.equals(password2)) {
                error_message = res.getString(R.string.missing_info);
            } else {
                error_message = res.getString(R.string.password_mismatch);
            }

            TextView txtView = (TextView) alertDialog.findViewById(R.id.txtView);
            txtView.setText(error_message);

            Button btn = (Button) alertDialog.findViewById(R.id.close_popup);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        } else {
            model.addUser(new User(realName, username, password1));
            startActivity(new Intent(view.getContext(), MainActivity.class));
        }
    }

    /**
     * Cancels the registration process and brings the User back to the main page of the app.
     *
     * @param view The default parameter for an onClick custom method.
     */
    public void onCancelRegister(View view) {
        super.onBackPressed();
    }

    private boolean isValid (String check) {
        boolean match = true;
        for (int i = 0; i < check.length()-1; i++) {
          if (check.substring(i,i+1).matches("[^A-Za-z0-9 ]")){
              match = false;
          }
        }
        return match;
    }

    private boolean isNew(String check) {
        boolean match = true;
        if (model.inTheList(check)) {
            match = false;
        }
        return match;
    }
}
