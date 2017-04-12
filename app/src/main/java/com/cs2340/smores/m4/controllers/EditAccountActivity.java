package com.cs2340.smores.m4.controllers;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        editEmailAddress = (EditText) this.findViewById(R.id.editEmailAddress);
        editPhoneNumber = (EditText) this.findViewById(R.id.editPhoneNumber);
        editAddress = (EditText) this.findViewById(R.id.editHomeAddress);

        editRealName.setText(user.getRealName());
        editUsername.setText(user.getUsername());
        editEmailAddress.setText(user.getEmail());
        editPhoneNumber.setText(user.getPhoneNumber());
        editAddress.setText(user.getAddress());
    }

    /**
     * onClick method to check the edits attempted to be made to the User's account.
     * If a parameter is empty, nothing is changed. If new information is supplied,
     * then it is checked against the same standards applied in the registration process.
     * If any information is badly formatted, an error is supplied. If all information
     * is correctly formatted, the User is updated and then returned to the home page of the app.
     *
     * @param view The Button's view.
     */
    public void onEditAccount(View view) {
        String realName = editRealName.getText().toString();
        String username = editUsername.getText().toString();
        String oldPassword = editOldPassword.getText().toString();
        String newPassword1 = editNewPassword1.getText().toString();
        String newPassword2 = editNewPassword2.getText().toString();
        String email = editEmailAddress.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();
        String address = editAddress.getText().toString();

        if ((((oldPassword.length() > 0) || (newPassword1.length() > 0)
                || (newPassword2.length() > 0)) && (!(newPassword1.equals(newPassword2))
                || (Model.checkLogin(user.getUsername(), oldPassword) == null)))
                || ((username.length() > 0)
                && ((!Model.isValid(username)) || (!Model.exists(username))))
                || ((email.length() == 0) || (phoneNumber.length() == 0)
                || (address.length() == 0) || (realName.length() == 0)
                || (username.length() == 0))) {
            Resources res = getResources();
            String errorMessage;
            if (!(newPassword1.equals(newPassword2))) {
                errorMessage = res.getString(R.string.new_password_mismatch);
            } else if (Model.checkLogin(user.getUsername(), oldPassword) == null) {
                errorMessage = res.getString(R.string.wrong_old_password);
            } else if (Model.exists(username)) {
                errorMessage = res.getString(R.string.username_taken);
            } else if (!Model.isValid(username)) {
                errorMessage = res.getString(R.string.invalid_username);
            } else {
                errorMessage = getString(R.string.general_missing_info);
            }

            new Error(view, errorMessage);
        } else {
            String oldUsername = user.getUsername();

            if ((oldPassword.length() > 0) || (newPassword1.length() > 0)
                    || (newPassword2.length() > 0)) {
                user.setPassword(oldPassword, newPassword1);
            }
            user.setRealName(realName);
            user.setUsername(username);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);
            Model.updateUser(user, oldUsername);

            super.onBackPressed();
        }
    }

    /**
     * Standard return method. Cancels the editing process,
     * changes no User information, and goes back to the home screen of the app.
     *
     * @param view the Button's view.
     */
    public void onCancelEditing(View view) {
        super.onBackPressed();
    }
}
