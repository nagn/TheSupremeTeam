package com.cs2340.smores.m4.model;

/**
 * Report descriptive of the quality of water in an area at a given time.
 */

public class QualityReport extends Report {

    private static int count = 0;
    private int reportNumber;
    private String waterType;
    private String condition;
    public static final String[] waterTypes =
            new String[]{"Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    public static final String[] waterConditions =
            new String[]{"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};

    public QualityReport(double longitude, double latitude, int type, int condition) {
        super(longitude, latitude);
        this.reportNumber = ++count;
        this.waterType = waterTypes[type];
        this.condition = waterConditions[condition];
    }

    public QualityReport(int reportNumber, String name, String timeCreated,
                         double longitude, double latitude, String waterType, String condition) {
        super(name, longitude, latitude, timeCreated);
        this.reportNumber = reportNumber;
        this.waterType = waterType;
        this.condition = condition;
    }

    public int getReportNumber() {
        return this.reportNumber;
    }

    public String getWaterType() {
        return this.waterType;
    }

    public String getCondition() {
        return this.condition;
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof QualityReport)
                && (((QualityReport) o).getReportNumber() == this.reportNumber));
    }
}
