package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.cs2340.smores.m4.R;

/**
 * List activity that allows Admins to manage Users.
 * Possible actions include unblocking locked-out Users, banning Users from submitting reports
 * of any kind, or deleting Users.
 */

public class ManageUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_activity);

        UserDetailAdapter adapter = new UserDetailAdapter(this);

        ListView lView = (ListView) findViewById(R.id.viewUserList);
        lView.setAdapter(adapter);
    }


    /**
     * Standard method to return the User to the home page of the app when
     * they are done managing all current Users in the system.
     */
    public void onReturn() {
        super.onBackPressed();
    }
}
