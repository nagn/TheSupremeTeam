package com.cs2340.smores.m4.model;

/**
 * Report descriptive of the purity of water in an area at a given time.
 */

public class PurityReport extends Report {

    private int virusPPM;
    private int contaminantPPM;
    private String condition;
    public static final String[] conditions = new String[]{"Safe", "Treatable", "Unsafe"};
    public static final String[] virusOrContaminant = new String[]{"Virus", "Contaminant"};

    /**
     * Constructor for a Purity Report. Takes in the location, condition, virus PPM, and
     * contaminant PPM of the water where this Source Report was left. All other info is
     * handled by the super call to the Report Constructor.
     *
     * @param location The location where this Source Report was left.
     * @param condition The condition of the water.
     * @param virusPPM The PPM of the viruses in the water where this Source Report was left.
     * @param contaminantPPM The PPM of contaminants in the water where this Source Report was left.
     */
    public PurityReport(String location, int virusPPM, int contaminantPPM, int condition) {
        super(location);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.condition = conditions[condition];
    }

    /**
     * Secondary constructor of a Purity Report, for use with the SQL databases.
     *
     * @param reportNumber The report number.
     * @param name The name of the reporter.
     * @param timeCreated The time the report was created in String format.
     * @param location The location of the report.
     * @param condition The condition of the water.
     * @param virusPPM The PPM of viruses in the water.
     * @param contaminantPPM The PPM of contaminants in the water.
     */
    PurityReport(int reportNumber, String name, String timeCreated, String location,
                        String condition, int virusPPM, int contaminantPPM) {
        super(name, location, timeCreated, reportNumber);
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
        this.condition = condition;
    }

    /**
     * Getter for the condition of the water where this Purity Report was left.
     *
     * @return The condition of the water.
     */
    public String getCondition() {
        return this.condition;
    }

    /**
     * Getter for the PPM of the viruses in the water where this Purity Report was left.
     *
     * @return The PPM of viruses where the Purity Report was left.
     */
    public int getVirusPPM() {
        return this.virusPPM;
    }

    /**
     * Getter for the PPM of the contaminants in the water where this Purity Report was left.
     *
     * @return The PPM of contaminants where the Purity Report was left.
     */
    public int getContaminantPPM() {
        return this.contaminantPPM;
    }
}
