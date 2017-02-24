package com.cs2340.smores.m4.model;

import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The model class containing all hierarchical data for the app.
 */

public class Model {

    //TODO: implement the log for the app!
    public static User user;
    public static ArrayList<User> users;
    public static ArrayList<QualityReport> qualityReports;
    public static ArrayList<PurityReport> purityReports;
    public static UserDBHandler userDBHandler;
    public static QualityReportDBHandler qualityReportDBHandler;
    public static PurityReportDBHandler purityReportDBHandler;


    public static void setUser(User user) {
        Model.user = user;
    }

    public static void addUser(User user) {
        users.add(user);
        userDBHandler.addUser(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
        userDBHandler.removeUser(user);
    }

    public static void updateUser(User user, String oldUsername) {
        userDBHandler.updateUser(user, oldUsername);
    }

    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static User checkLogin(String username, String password) {
        String logMessage = "Time of Login Attempt: " + DateFormat.getDateTimeInstance()
                .format(new Date()) + ", User: " + username + ", Status: ";
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).unlock(username, password)) {
                Log.d("Login", logMessage + "Success");
                return users.get(i);
            }
        }
        Log.d("Login", logMessage + ((exists(username)) ? "Bad Password" : "Unknown ID"));
        return null;
    }

    public static boolean isValid(String username) {
        for (int i = 0; i < username.length() - 1; i++) {
            if (username.substring(i, i + 1).matches("[^A-Za-z0-9]")) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNew(String username) {
        return !(Model.exists(username));
    }

    public static boolean exists(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void addQualityReport(QualityReport report) {
        if (report != null) {
            qualityReports.add(report);
            qualityReportDBHandler.addReport(report);
        }
    }

    public static void removeQualityReport(QualityReport report) {
        if ((report != null) && (user != null) && (user.getType() >= 2)) {
            Log.d("Deleted Report", "Time of Deletion: " + DateFormat.getDateTimeInstance()
                    .format(new Date()) + ", Admin: " + user.getUsername()
                    + ", Report Number: " + report.getReportNumber());
            qualityReports.remove(report);
            qualityReportDBHandler.removeReport(report);
        }
    }

    public static void addPurityReport(PurityReport report) {
        if (report != null) {
            purityReports.add(report);
            purityReportDBHandler.addReport(report);
        }
    }

    public static void removePurityReport(PurityReport report, User user) {
        if ((report != null) && (user != null) && (user.getType() >= 2)) {
            Log.d("Deleted Report", "Time of Deletion: " + DateFormat.getDateTimeInstance()
                    .format(new Date()) + ", Admin: " + user.getUsername()
                    + ", Report Number: " + report.getReportNumber());
            purityReports.remove(report);
            purityReportDBHandler.removeReport(report);
        }
    }
}
