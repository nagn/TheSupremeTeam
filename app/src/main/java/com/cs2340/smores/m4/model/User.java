package com.cs2340.smores.m4.model;

import java.lang.reflect.Array;

/**
 * User object for the project.
 *
 * @author Samuel Mohr
 * @version 1.0
 */
public class User {

    private final String realName;
    private final String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isWorker;
    private boolean isManager;
    private boolean isAdmin;
    public static String[] userTypes = new String[]{"Customer", "Worker", "Manager", "Admin"};

    /**
     * Constructor for a User.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     */
    public User(String realName, String username, String password) {
        this(realName, username, password, 0);
    }

    /**
     * Constructor for a User.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     * @param userType The type of User being created.
     */
    public User(String realName, String username, String password, int userType) {
        this(realName, username, password, userType, "Unknown", "Unknown", "Unknown");
    }

    public User(String realName, String username, String password, int userType, String email,
                String phoneNumber, String address) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        switch (userType) {
            case 3:
                this.setAdmin();
            case 2:
                this.setManager();
            case 1:
                this.setWorker();
            default:
                break;
        }
    }

    /**
     * Checker if the given credentials match this User's name and password.
     * @param username The proposed username.
     * @param password The matching proposed password.
     * @return Whether the login info is correct.
     */
    public boolean unlock(String username, String password) {
        return ((this.username.equals(username)) && (this.password.equals(password)));
    }

    /**
     * Setter for the User's password. Requires the old password.
     * @param oldPassword The current password of the User.
     * @param newPassword The new password for the User to use.
     */
    public boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    /**
     * Setter for whether the User is a Worker.
     */
    public void setWorker() {
        this.isWorker = true;
    }

    /**
     * Setter for whether the User is a Manager.
     */
    public void setManager() {
        this.isWorker = true;
        this.isManager = true;
    }

    /**
     * Setter for whether the User is an Admin.
     */
    public void setAdmin() {
        this.isWorker = true;
        this.isManager = true;
        this.isAdmin = true;
    }

    /**
     * Getter for the User's real name.
     * @return The User's real name.
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * Getter for the User's username.
     * @return The User's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter for the User's password.  Only accessible by Admins and the user themselves.
     * @param user The User accessing the data.
     * @return Either the User's password or a null String.
     */
    public String getPassword(User user) {
        if ((user.isAdmin()) || (user.equals(this))) {
            return this.password;
        }
        return null;
    }

    /**
     * Getter for whether the User is a Worker.
     * @return Whether the User is a Worker.
     */
    public boolean isWorker() {
        return this.isWorker;
    }

    /**
     * Getter for whether the User is a Manager.
     * @return Whether the User is a Manager.
     */
    public boolean isManager() {
        return this.isManager;
    }

    /**
     * Getter for whether the User is an Admin.
     * @return Whether the User is an Admin.
     */
    public boolean isAdmin() {
        return this.isAdmin;
    }

    /**
     * Converter to give the equivalent int for a User type.
     * @return an int represenative of the User type of the User:
     * 3 = Admin, 2 = Manager, 1 = Worker, and 0 = Customer
     */
    public int userTypeToInt() {
        if (this.isAdmin) {
            return 3;
        } else if (this.isManager) {
            return 2;
        } else if (this.isWorker) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Checker for whether this User object equals another.
     * @param user The other User.
     * @return Whether this User equals the other User.
     */
    private boolean equals(User user) {
        return (this.username.equals(user.username));
    }
}