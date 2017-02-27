package com.cs2340.smores.m4.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The model class containing all hierarchical data for the app.
 */

public class Model {

    public static User user;
    public static ArrayList<User> users;
    public static ArrayList<SourceReport> sourceReports;
    public static ArrayList<PurityReport> purityReports;
    public static ArrayList<String> log;
    public static UserDBHandler userDBHandler;
    public static SourceReportDBHandler sourceReportDBHandler;
    public static PurityReportDBHandler purityReportDBHandler;
    public static LogDBHandler logDBHandler;

    /**
     * Setter for the current active User of the app.
     *
     * @param user The current User of the app.
     */
    public static void setUser(User user) {
        Model.user = user;
    }

    /**
     * Add the given User to the system.
     *
     * @param user The User to add to the system.
     */
    public static void addUser(User user) {
        users.add(user);
        userDBHandler.addUser(user);
    }

    /**
     * Remove the provided User from the system. Only possible to perform by
     * an Admin, so one must be supplied. The event is logged.
     *
     * @param user The User to delete from the system.
     * @param admin The Admin that is deleting the User.
     */
    public static void removeUser(User user, User admin) {
        if ((user != null) && (admin != null) && (admin.getType() >= 3)) {
            addLog("Account Deletion -- Time of Removal: "
                    + DateFormat.getDateTimeInstance().format(new Date()) + ", Admin: "
                    + admin.getUsername() + ", Deleted User: " + user.getUsername());
            users.remove(user);
            userDBHandler.removeUser(user);
        }
    }

    /**
     * Updates the User when EditAccountActivity is used.
     *
     * @param user The user to update in the system.
     * @param oldUsername The old username of the updated User.
     */
    public static void updateUser(User user, String oldUsername) {
        userDBHandler.updateUser(user, oldUsername);
    }

    /**
     * Get the User from the system with the matching username to the one provided.
     * If no User has a matching username to the provided one, a null User is returned.
     *
     * @param username The username to search with.
     * @return Either the User with the matching username or a null User.
     */
    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Getter for the number of Users in the system.
     *
     * @return The number of Users in the system.
     */
    public static int numberOfUsers() {
        return users.size();
    }

    /**
     * Checks the login info provided. If correct, the matching User is returned.
     * If not, a null User is returned. The event is logged.
     *
     * @param username THe provided username.
     * @param password The provided password.
     * @return The proper User or a null User.
     */
    public static User checkLogin(String username, String password) {
        String logMessage = "Login -- Time of Login Attempt: " + DateFormat.getDateTimeInstance()
                .format(new Date()) + ", User: " + username + ", Status: ";
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).unlock(username, password)) {
                addLog(logMessage + "Success");
                return users.get(i);
            }
        }
        addLog(logMessage + ((exists(username)) ? "Bad Password" : "Unknown ID"));
        return null;
    }

    /**
     * Uses regex to check if the given username is allowed in the system.
     *
     * @param username The username to check the formatting of.
     * @return Whether the username is formatted correctly.
     */
    public static boolean isValid(String username) {
        for (int i = 0; i < username.length() - 1; i++) {
            if (username.substring(i, i + 1).matches("[^A-Za-z0-9]")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given username is new to the system.
     *
     * @param username The username to check the novelty of.
     * @return Whether the username is new to the system.
     */
    public static boolean isNew(String username) {
        return !(Model.exists(username));
    }

    /**
     * Getter for whether a User exists in the system wih the given username.
     *
     * @param username The username to check the existence of.
     * @return Whether a User exists in the system with this username.
     */
    public static boolean exists(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the provided Purity Report to the system.
     *
     * @param report The Purity Report to add to the system.
     */
    public static void addSourceReport(SourceReport report) {
        if (report != null) {
            sourceReports.add(report);
            sourceReportDBHandler.addReport(report);
        }
    }

    /**
     * Removes the provided Source Report from the system and logs the event.
     *
     * @param report The Source Report to remove from the system.
     */
    public static void removeSourceReport(SourceReport report) {
        if ((report != null) && (user != null) && (user.getType() >= 2)) {
            addLog("Deleted Report -- Time of Deletion: " + DateFormat.getDateTimeInstance()
                    .format(new Date()) + ", Admin: " + user.getUsername()
                    + ", Report Number: " + report.getReportNumber());
            sourceReports.remove(report);
            sourceReportDBHandler.removeReport(report);
        }
    }

    /**
     * Adds the provided Purity Report to the system.
     *
     * @param report The Purity Report to add to the system.
     */
    public static void addPurityReport(PurityReport report) {
        if (report != null) {
            purityReports.add(report);
            purityReportDBHandler.addReport(report);
        }
    }

    /**
     * Removes the provided Purity Report from the system and logs the event.
     *
     * @param report The Purity Report to remove from the system.
     */
    public static void removePurityReport(PurityReport report) {
        if ((report != null) && (user != null) && (user.getType() >= 2)) {
            addLog("Deleted Report -- Time of Deletion: " + DateFormat.getDateTimeInstance()
                    .format(new Date()) + ", Admin: " + user.getUsername()
                    + ", Report Number: " + report.getReportNumber());
            purityReports.remove(report);
            purityReportDBHandler.removeReport(report);
        }
    }

    /**
     * Adds a new log message to the log.
     *
     * @param logInfo The log message to add to the log.
     */
    static void addLog(String logInfo) {
        log.add(logInfo);
        logDBHandler.addLog(log.indexOf(logInfo), logInfo);
    }
}
