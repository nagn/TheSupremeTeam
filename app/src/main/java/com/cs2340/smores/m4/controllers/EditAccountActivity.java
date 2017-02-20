package com.cs2340.smores.m4.controllers;

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

/**
 * Standard activity to edit a User's account in the app once logged in.
 */

public class EditAccountActivity extends AppCompatActivity {

    private EditText editRealName;
    private EditText editUsername;
    private EditText editOldPassword;
    private EditText editNewPassword1;
    private EditText editNewPassword2;
    private EditText editEmailAddress;
    private EditText editPhoneNumber;
    private EditText editAddress;
    private Spinner userTypeSpinner;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account_activity);

        user = Model.user;

        editRealName = (EditText) this.findViewById(R.id.editRealName);
        editUsername = (EditText) this.findViewById(R.id.editUsername);
        editOldPassword = (EditText) this.findViewById(R.id.editOldPassword);
        editNewPassword1 = (EditText) this.findViewById(R.id.editPassword1);
        editNewPassword2 = (EditText) this.findViewById(R.id.editPassword2);
        userTypeSpinner = (Spinner) this.findViewById(R.id.userTypeSpinner);
        editEmailAddress = (EditText) this.findViewById(R.id.editEmailAddress);
        editPhoneNumber = (EditText) this.findViewById(R.id.editPhoneNumber);
        editAddress = (EditText) this.findViewById(R.id.editHomeAddress);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, User.userTypes);
        userTypeSpinner.setAdapter(adapter);
        userTypeSpinner.setSelection(user.userTypeToInt());
    }

    public void onEditAccount(View view) {
        String realName = editRealName.getText().toString();
        String username = editUsername.getText().toString();
        String oldPassword = editOldPassword.getText().toString();
        String newPassword1 = editNewPassword1.getText().toString();
        String newPassword2 = editNewPassword2.getText().toString();
        String email = editEmailAddress.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();
        String address = editAddress.getText().toString();
        int userType = userTypeSpinner.getSelectedItemPosition();

        if ((((oldPassword.length() > 0) || (newPassword1.length() > 0)
                || (newPassword2.length() > 0)) && (!(newPassword1.equals(newPassword2))
                || (Model.checkLogin(user.getUsername(), oldPassword) == null)))
                || ((username.length() > 0)
                && ((!Model.isValid(username)) || (!Model.isNew(username))))) {
            Resources res = getResources();
            String errorMessage;
            if (!(newPassword1.equals(newPassword2))) {
                errorMessage = res.getString(R.string.new_password_mismatch);
            } else if (Model.checkLogin(user.getUsername(), oldPassword) == null) {
                errorMessage = res.getString(R.string.wrong_old_password);
            } else if (!Model.isNew(username)) {
                errorMessage = res.getString(R.string.username_taken);
            } else {
                errorMessage = res.getString(R.string.invalid_username);
            }

            new Error(view, errorMessage);
        } else {
            Model.removeUser(user);

            if ((oldPassword.length() > 0) || (newPassword1.length() > 0)
                    || (newPassword2.length() > 0)) {
                user.setPassword(oldPassword, newPassword1);
            }

            if (realName.length() > 0) {
                user.setRealName(realName);
            }
            if (username.length() > 0) {
                user.setUsername(username);
            }
            if (email.length() > 0) {
                user.setEmail(email);
            }
            if (phoneNumber.length() > 0) {
                user.setPhoneNumber(phoneNumber);
            }
            if (address.length() > 0) {
                user.setAddress(address);
            }
            user.setUserType(userType);

            Model.addUser(user);
            Model.setUser(user);

            super.onBackPressed();
        }
    }

    public void onCancelEditing(View view) {
        super.onBackPressed();
    }
}
