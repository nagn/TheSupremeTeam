package com.cs2340.smores.m4.controllers;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.User;
import com.cs2340.smores.m4.model.Customer;
import com.cs2340.smores.m4.model.Worker;
import com.cs2340.smores.m4.model.Manager;
import com.cs2340.smores.m4.model.Admin;

/**
 * The activity class for the registration page and process of the app.
 */

public class RegisterActivity extends AppCompatActivity {

    private EditText editRealName;
    private EditText editUsername;
    private EditText editPassword1;
    private EditText editPassword2;
    private EditText editEmailAddress;
    private EditText editPhoneNumber;
    private EditText editAddress;
    private Spinner userTypeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        editRealName = (EditText) this.findViewById(R.id.editRealName);
        editUsername = (EditText) this.findViewById(R.id.editUsername);
        editPassword1 = (EditText) this.findViewById(R.id.editPassword1);
        editPassword2 = (EditText) this.findViewById(R.id.editPassword2);
        userTypeSpinner = (Spinner) this.findViewById(R.id.userTypeSpinner);
        editEmailAddress = (EditText) this.findViewById(R.id.editEmailAddress);
        editPhoneNumber = (EditText) this.findViewById(R.id.editPhoneNumber);
        editAddress = (EditText) this.findViewById(R.id.editHomeAddress);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, User.userTypes);
        userTypeSpinner.setAdapter(adapter);
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
        String email = editEmailAddress.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();
        String address = editAddress.getText().toString();
        int userType = userTypeSpinner.getSelectedItemPosition();

        if (!(password1.equals(password2))
                || (realName.length() == 0) || (username.length() == 0)
                || (password1.length() == 0) || (password2.length() == 0)
                || (!Model.isValid(username)) || (!Model.isNew(username))) {
            Resources res = getResources();
            String errorMessage;

            if (!Model.isNew(username)) {
                errorMessage = res.getString(R.string.username_taken);
            } else if (!Model.isValid(username)) {
                errorMessage = res.getString(R.string.invalid_username);
            } else if (!password1.equals(password2)) {
                errorMessage = res.getString(R.string.password_mismatch);
            } else {
                errorMessage = res.getString(R.string.missing_info);
            }

            new Error(view, errorMessage);
        } else {
            if (email.length() == 0) {
                email = "Unknown";
            }
            if (phoneNumber.length() == 0) {
                phoneNumber = "Unknown";
            }
            if (address.length() == 0) {
                address = "Unknown";
            }

            User user;
            switch (userType) {
                case 3:
                    user = new Admin(realName, username, password1, email, phoneNumber, address);
                    break;
                case 2:
                    user = new Worker(realName, username, password1, email, phoneNumber, address);
                    break;
                case 1:
                    user = new Manager(realName, username, password1, email, phoneNumber, address);
                    break;
                default:
                    user = new Customer(realName, username, password1, email, phoneNumber, address);
                    break;
            }

            Model.addUser(user);
            startActivity(new Intent(view.getContext(), MainActivity.class));
            finish();
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
}
