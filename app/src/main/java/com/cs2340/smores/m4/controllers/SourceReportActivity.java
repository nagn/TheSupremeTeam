package com.cs2340.smores.m4.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.SourceReport;
import com.cs2340.smores.m4.model.Report;

/**
 * Activity to submit a water quality report.
 * Time of report and other info is collected automatically.
 */

public class SourceReportActivity extends AppCompatActivity {

    private Spinner typeSpinner;
    private Spinner conditionSpinner;
    private Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source_report_activity);

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        typeSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, SourceReport.waterTypes));
        conditionSpinner = (Spinner) findViewById(R.id.conditionSpinner);
        conditionSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, SourceReport.waterConditions));
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        locationSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                Report.locations.keySet().toArray(new String[Report.locations.size()])));

    }

    /**
     * Submission method for all Source Reports. When pressed,
     * a new Purity Report is added to the Model with all of the selected info.
     *
     * @param view The submit Button's view.
     */
    public void onSubmitReport(View view) {
        Model.addSourceReport(new SourceReport(
                locationSpinner.getSelectedItem().toString(),
                typeSpinner.getSelectedItemPosition(),
                conditionSpinner.getSelectedItemPosition()));
        super.onBackPressed();
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
