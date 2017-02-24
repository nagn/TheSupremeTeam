package com.cs2340.smores.m4.model;

import android.location.Location;

import com.cs2340.smores.m4.controllers.MapsActivity;
import com.google.android.gms.location.LocationListener;

import java.text.DateFormat;
import java.util.Date;

/**
 * Abstract superclass for all reports (quality and purity).
 */

public abstract class Report {

    private final static DateFormat dateFormat = DateFormat.getDateTimeInstance();
    private String timeCreated;
    private String name;
    private double longitude;
    private double latitude;
    public static int count = 0;


    Report(double longitude, double latitude) {
        this.timeCreated = dateFormat.format(new Date());
        this.name = Model.user.getRealName();
        this.longitude = longitude;
        this.latitude = latitude;
    }

    Report(String name, double longitude, double latitude, String timeCreated) {
        this.timeCreated = timeCreated;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTimeCreated() {
        return this.timeCreated;
    }

    public String getName() {
        return this.name;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

//    private class MyLocationListener extends MapsActivity implements LocationListener {
//
//        @Override
//        public void onLocationChanged(Location location) {
//
//        }
//    }
}
