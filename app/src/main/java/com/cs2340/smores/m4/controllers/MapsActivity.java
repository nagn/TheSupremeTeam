package com.cs2340.smores.m4.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.cs2340.smores.m4.R;

import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.SourceReport;
import com.cs2340.smores.m4.model.Report;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = Report.locations.get("Atlanta");
        for (SourceReport report : Model.sourceReports) {
            latLng = Report.locations.get(report.getLocation());
            googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude
                    + Math.random() * 0.1, latLng.longitude + Math.random() * 0.1))
                    .title(report.getWaterType() + " / " + report.getCondition()));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    /**
     * Standard method to return the User to the home page of the app when
     * they are done viewing all current source reports in the system.
     *
     * @param view The Button's view.
     */
    public void onReturn(View view) {
        super.onBackPressed();
    }
}
