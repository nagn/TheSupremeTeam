package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.QualityReport;

/**
 * Activity to submit a water quality report.
 * Time of report and other info is collected automatically.
 */

public class QualityReportActivity extends AppCompatActivity {

    private Spinner qualitySpinner;
    private Spinner conditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quality_report_activity);

        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        qualitySpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, QualityReport.waterTypes));
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        conditionSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, QualityReport.waterConditions));
    }

    //TODO: get the location of the User for the report!
    public void onSubmitReport(View view) {
        Model.addQualityReport(new QualityReport(33.73 + Math.random() * 0.3,
                -84.41 + Math.random() * 0.3,
                qualitySpinner.getSelectedItemPosition(),
                conditionSpinner.getSelectedItemPosition()));
        super.onBackPressed();
    }

    public void onCancelReport(View view) {
        super.onBackPressed();
    }
}
