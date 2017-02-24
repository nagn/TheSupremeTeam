package com.cs2340.smores.m4.model;

/**
 * Report descriptive of the purity of water in an area at a given time.
 */

public class PurityReport extends Report {


    private int reportNumber;
    private int virusPPM;
    private int contaminantPPM;
    private String condition;
    public static final String[] conditions = new String[]{"Safe", "Treatable", "Unsafe"};

    public PurityReport(double longitude, double latitude,
                        int virusPPM, int contaminantPPM, int condition) {
        super(longitude, latitude);
        this.reportNumber = ++Report.count;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.condition = conditions[condition];
    }

    public PurityReport(int reportNumber, String name, String timeCreated, double longitude,
                        double latitude, String condition, int virusPPM, int contaminantPPM) {
        super(name, longitude, latitude, timeCreated);
        this.reportNumber = reportNumber;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.condition = condition;
    }

    public int getReportNumber() {
        return this.reportNumber;
    }

    public String getCondition() {
        return this.condition;
    }

    public int getVirusPPM() {
        return this.virusPPM;
    }

    public int getContaminantPPM() {
        return this.contaminantPPM;
    }

    @Override
    public boolean equals(Object o) {
        return ((o instanceof PurityReport)
                && (((PurityReport) o).getReportNumber() == this.reportNumber));
    }

}
