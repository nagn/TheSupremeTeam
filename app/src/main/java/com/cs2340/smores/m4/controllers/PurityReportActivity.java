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
import com.cs2340.smores.m4.model.Report;

/**
 * Activity to submit a water purity report.
 * Time of report and other info is collected automatically.
 */

public class PurityReportActivity extends AppCompatActivity {

    private Spinner conditionSpinner, locationSpinner;
    private EditText editVirus, editContaminant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purity_report_activity);

        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        conditionSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, PurityReport.conditions));
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        locationSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                Report.locations.keySet().toArray(new String[Report.locations.size()])));
        editVirus = (EditText) findViewById(R.id.editVirusPPM);
        editContaminant = (EditText) findViewById(R.id.editContaminantPPM);
    }

    /**
     * Submission method for all Purity Reports. If any of the fields are empty
     * or at least one of the given PPM's is not an integer, an appropriate error message
     * is presented to the User. If all information is inputted and formatted correctly,
     * a new Purity Report is added to the Model.
     *
     * @param view The submit Button's view.
     */
    public void onSubmitReport(View view) {
        String virusPPM = editVirus.getText().toString();
        String contaminantPPM = editContaminant.getText().toString();
        int numVirusPPM, numContaminantPPM;
        if ((virusPPM.length() == 0) || (contaminantPPM.length() == 0)) {
            new Error(view, getResources().getString(R.string.general_missing_info));
        } else {
            try {
                numVirusPPM = Integer.parseInt(virusPPM);
                numContaminantPPM = Integer.parseInt(contaminantPPM);
                Model.addPurityReport(new PurityReport(locationSpinner
                        .getSelectedItem().toString(), numVirusPPM, numContaminantPPM,
                        conditionSpinner.getSelectedItemPosition()));
                super.onBackPressed();
            } catch (NumberFormatException nfe) {
                new Error(view, getResources().getString(R.string.bad_PPMs));
            }
        }
    }

    /**
     * Standard return method. Cancels the editing process,
     * adds no Report, and goes back to the home screen of the app.
     *
     * @param view the cancel Button's view.
     */
    public void onCancelReport(View view) {
        super.onBackPressed();
    }
}
