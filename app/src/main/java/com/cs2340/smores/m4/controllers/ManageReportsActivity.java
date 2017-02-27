package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;

/**
 * List activity that allows Admins to manage all Reports, allowing select deletions.
 */

public class ManageReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_reports_activity);

        ListView sourceList = (ListView) findViewById(R.id.sourceReportList);
        sourceList.setAdapter(new SourceReportDetailAdapter(this, Model.sourceReports));

        ListView purityList = (ListView) findViewById(R.id.purityReportList);
        purityList.setAdapter(new PurityReportDetailAdapter(this, Model.purityReports));
    }

    /**
     * Standard method to return the User to the home page of the app when
     * they are done managing all current reports in the system.
     *
     * @param view The Button's view.
     */
    public void onReturn(View view) {
        super.onBackPressed();
    }
}
