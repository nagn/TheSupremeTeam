package com.cs2340.smores.m4.model;

import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Abstract superclass for all Reports (Source and Purity).
 */

public abstract class Report {

    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private String timeCreated, name, location;
    private int reportNumber;
    private static int count = 0;
    public static HashMap<String, LatLng> locations = new HashMap<>();
    public static final String[] years = new String[]{"2010", "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018", "2019", "2020"};

    //Adds all of the current known locations to te locations HashMap on opening the app.
    static {
        locations.put("Atlanta", new LatLng(33.7490, -84.3880));
        locations.put("New York City", new LatLng(40.7128, -74.0059));
        locations.put("Boston", new LatLng(42.3601, -71.0589));
        locations.put("San Francisco", new LatLng(37.7749, -122.4194));
        locations.put("St. Louis", new LatLng(38.6270, -90.1994));
        locations.put("Boston", new LatLng(42.3601, -71.0589));
        locations.put("Boston", new LatLng(42.3601, -71.0589));
        locations.put("Boston", new LatLng(42.3601, -71.0589));
        locations.put("Boston", new LatLng(42.3601, -71.0589));
    }

    /**
     * Constructor for an Report. Takes the location, yet collects the time created,
     * report number, and the name of the User that left it automatically.
     *
     * @param location The location of where the Report was left.
     */
    Report(String location) {
        this.timeCreated = dateFormat.format(new Date());
        this.name = Model.user.getRealName();
        this.location = location;
        this.reportNumber = ++count;
    }

    /**
     * Secondary constructor of a Report, for use with the SQL databases.
     *
     * @param name The name of the User that left this report.
     * @param location The location at which this Report was left.
     * @param timeCreated The time this Report was left in String format.
     * @param reportNumber The number of this Report.
     */
    Report(String name, String location, String timeCreated, int reportNumber) {
        this.name = name;
        this.location = location;
        this.timeCreated = timeCreated;
        this.reportNumber = reportNumber;
    }

    /**
     * Getter for the report number of the Report.
     *
     * @return The report number.
     */
    public int getReportNumber() {
        return this.reportNumber;
    }

    /**
     * Getter for the time this Report was created in String format.
     *
     * @return The time this Report was created, in String format.
     */
    public String getTimeCreated() {
        return this.timeCreated;
    }

    /**
     * Getter for the name of the User that left this Report.
     *
     * @return The name of the User that left this Report.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the location that this Report was left.
     *
     * @return The location where this Report was left.
     */
    public String getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof Report)
                && (((Report) o).getReportNumber() == this.reportNumber));
    }
}
