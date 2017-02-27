package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;

/**
 * Activity to allow Admins to see the list of all current logs.
 */

public class ViewLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_log_activity);

        ListAdapter adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, Model.log);

        ListView lView = (ListView) findViewById(R.id.viewLogList);
        lView.setAdapter(adapter);
    }

    /**
     * Standard return method for when the User is finished viewing the security logs.
     * Returns the User to the home screen of the app.
     *
     * @param view The return Button's view.
     */
    public void onReturn(View view) {
        super.onBackPressed();
    }
}
