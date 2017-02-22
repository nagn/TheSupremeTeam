package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.PurityReport;

/**
 * Activity to submit a water purity report.
 * Time of report and other info is collected automatically.
 */

public class PurityReportActivity extends AppCompatActivity {

    private Spinner conditionSpinner;
    private EditText editVirus;
    private EditText editContaminant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quality_report_activity);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        conditionSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, PurityReport.conditions));
        editVirus = (EditText) findViewById(R.id.editVirusPPM);
        editContaminant = (EditText) findViewById(R.id.editContaminantPPM);
    }

    //TODO: get the location of the User for the report!
    public void onSubmitReport(View view) {
        String virusPPM = editVirus.getText().toString();
        String contaminantPPM = editContaminant.getText().toString();
        int numericVirusPPM;
        int numericContaminantPPM;
        if ((virusPPM.length() == 0) || (contaminantPPM.length() == 0)) {
            new Error(view, getResources().getString(R.string.general_missing_info));
        }
        try {
            numericVirusPPM = Integer.parseInt(virusPPM);
            numericContaminantPPM = Integer.parseInt(contaminantPPM);
            Model.addPurityReport(new PurityReport(33.73 + Math.random() * 0.3,
                    -84.41 + Math.random() * 0.3, numericVirusPPM,
                    numericContaminantPPM, conditionSpinner.getSelectedItemPosition()));
            super.onBackPressed();
        } catch (NumberFormatException nfe) {
            new Error(view, getResources().getString(R.string.bad_PPMs));
        }
    }

    public void onCancelReport(View view) {
        super.onBackPressed();
    }
}
