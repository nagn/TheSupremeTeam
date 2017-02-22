package com.cs2340.smores.m4.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.cs2340.smores.m4.R;

import com.cs2340.smores.m4.model.Model;
import com.cs2340.smores.m4.model.QualityReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LatLng latLng;

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
        for (QualityReport report : Model.qualityReports) {
            latLng = new LatLng(report.getLatitude(), report.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title(report.getWaterType() + " / " + report.getCondition()));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
