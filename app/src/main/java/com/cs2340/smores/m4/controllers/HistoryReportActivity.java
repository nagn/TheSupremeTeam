package com.cs2340.smores.m4.controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cs2340.smores.m4.R;
import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.PurityReport;
import com.cs2340.smores.m4.model.Report;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that allows Users of type Manager or higher permission to see trends in water purity.
 */

public class HistoryReportActivity extends AppCompatActivity {

    private Spinner chooseLocation, chooseVorC, chooseYear;
    private GraphView graph;
    private List<List<Integer>> months;
    private boolean isReady;
    PointsGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_report);

        isReady = false;

        chooseLocation = (Spinner) findViewById(R.id.chooseLocation);
        chooseLocation.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Report.locations.keySet().toArray()));
        chooseVorC = (Spinner) findViewById(R.id.chooseVorP);
        chooseVorC.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, PurityReport.virusOrContaminant));
        chooseYear = (Spinner) findViewById(R.id.chooseYear);
        chooseYear.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_item, Report.years));
        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(500);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Months");
        graph.getGridLabelRenderer().setVerticalAxisTitle("PPM");
//        graph.setScaleX(0.2f);
//        graph.setScaleY(0.8f);

        months = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            months.add(new ArrayList<Integer>());
        }

        series = new PointsGraphSeries<>();
        series.setColor(Color.RED);
        series.setShape(PointsGraphSeries.Shape.POINT);
        series.setSize(5);
        graph.addSeries(series);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        chooseLocation.setOnItemSelectedListener(listener);
        chooseVorC.setOnItemSelectedListener(listener);
        chooseYear.setOnItemSelectedListener(listener);

        isReady = true;

        update();
    }

    /**
     * Updates the series of data currently displayed on the graph. Called whenever
     * the User changes a Spinner's value.
     */
    private void update() {
        if (!isReady) {
            return;
        }
        boolean isVirus = (chooseVorC.getSelectedItemPosition() == 0);
        String time;
        DataPoint[] points = new DataPoint[12];
        for (PurityReport report : Model.purityReports) {
            time = report.getTimeCreated();
            if ((time.contains((String) chooseYear.getSelectedItem()))
                    && (report.getLocation().equals(chooseLocation.getSelectedItem()))) {
                months.get(Integer.parseInt(time.substring(5, 7)))
                        .add((isVirus) ? report.getVirusPPM() : report.getContaminantPPM());
            }
        }
        int sum;
        for (int i = 0; i < 12; i++) {
            sum = 0;
            for (int j : months.get(i)) {
                sum += j;
            }
            points[i] = new DataPoint(i + 1, (months.get(i).size() == 0)
                    ? 0 : sum / months.get(i).size());
        }
        graph.setTitle("Avg. " + ((isVirus) ? "Virus" : "Contam.") + " PPM/Month in "
                + chooseLocation.getSelectedItem());

        series.resetData(points);
    }

    /**
     * Standard return method. Cancels the viewing process
     * and goes back to the home screen of the app.
     *
     * @param view the Button's view.
     */
    public void onReturn(View view) {
        super.onBackPressed();
    }
}
