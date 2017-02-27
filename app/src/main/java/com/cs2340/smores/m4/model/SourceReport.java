package com.cs2340.smores.m4.model;

/**
 * Report descriptive of the quality of a water source in an area at a given time.
 */

public class SourceReport extends Report {

    private String waterType;
    private String condition;
    public static final String[] waterTypes =
            new String[]{"Bottled", "Well", "Stream", "Lake", "Spring", "Other"};
    public static final String[] waterConditions =
            new String[]{"Waste", "Treatable-Clear", "Treatable-Muddy", "Potable"};

    /**
     * Constructor for a Source Report. Takes in the location, water type, and condition
     * of the water where this Source Report was left. All other info is handled by the
     * super call to the Report Constructor.
     *
     * @param location The location where this Source Report was left.
     * @param type The type of water.
     * @param condition The condition of the water.
     */
    public SourceReport(String location, int type, int condition) {
        super(location);
        this.waterType = waterTypes[type];
        this.condition = waterConditions[condition];
    }

    /**
     * Secondary constructor of a Source Report, for use with the SQL databases.
     *
     * @param reportNumber The report number.
     * @param name The name of the reporter.
     * @param timeCreated The time the report was created in String format.
     * @param location The location of the report.
     * @param waterType The type of water.
     * @param condition The condition of the water.
     */
    SourceReport(int reportNumber, String name, String timeCreated,
                         String location, String waterType, String condition) {
        super(name, location, timeCreated, reportNumber);
        this.waterType = waterType;
        this.condition = condition;
    }

    /**
     * Getter for the type of water where this Source Report was left.
     *
     * @return The type of water where this Source Report was left.
     */
    public String getWaterType() {
        return this.waterType;
    }

    /**
     * Getter for the condition of the water where this Source Report was left.
     *
     * @return The condition of the water in this Source Report.
     */
    public String getCondition() {
        return this.condition;
    }
}
