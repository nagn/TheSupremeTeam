package com.cs2340.smores.m4.model;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * User object for the project.
 *
 * @author Samuel Mohr
 * @version 1.0
 */
public class User {

    private String realName, username, password, email, phoneNumber, address;
    private boolean isBanned, isLocked;
    private int userType;

    public final static String[] userTypes = new String[]{"Customer", "Worker", "Manager", "Admin"};

    /**
     * Constructor for the abstract superclass User.
     * Takes in all required information.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     * @param email The email of the user.
     * @param phoneNumber The phone number of the User.
     * @param address The address of the User.
     */
    public User(String realName, String username, String password,
            String email, String phoneNumber, String address, int userType) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
        this.isBanned = false;
        this.isLocked = false;
    }

    /**
     * Constructor for the abstract superclass User.
     * Takes in all required information.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     * @param email The email of the user.
     * @param phoneNumber The phone number of the User.
     * @param address The address of the User.
     */
    public User(String realName, String username, String password, String email,
                String phoneNumber, String address, boolean isBanned, boolean isLocked, int type) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = type;
        this.isBanned = isBanned;
        this.isLocked = isLocked;
    }


    /**
     * Checker if the given credentials match this User's name and password.
     * @param username The proposed username.
     * @param password The matching proposed password.
     * @return Whether the login info is correct.
     */
    boolean unlock(String username, String password) {
        return ((this.username.equals(username)) && (this.password.equals(password)));
    }

    /**
     * Method to lock out a user from login based on attempts.
     */
    public void ban(User user) {
        if ((user != null) && (user.getType() >= 3)) {
            this.isBanned = true;
            Log.d("Ban", "Time of Ban: " + DateFormat.getDateTimeInstance().format(new Date())
                    + ", Admin: " + user.getUsername() + ", Banned User: " + this.getUsername());
        }
    }

    public boolean isBanned() {
        return this.isBanned;
    }

    public void lockOut() {
        this.isLocked = true;
    }

    public void unblock(User user) {
        if ((user != null) && (user.getType() >= 3)) {
            this.isLocked = false;
            Log.d("Unblock", "Time of Unblock: " + DateFormat.getDateTimeInstance()
                    .format(new Date()) + ", Admin: " + user.getUsername()
                    + ", Unblocked User: " + this.getUsername());
        }
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Setter for the User's real name.
     * @param realName The new real name of the User.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Setter for the username of the User.
     * @param username The username of the User.
     */
    public void setUsername(String username) {
        this.username = username;
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
     * Setter for the email address of the User.
     * @param email The new email address for the User.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter for the phone number of the User.
     * @param phoneNumber The new phone number for the User.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter for the address of the User.
     * @param address The new address of the User.
     */
    public void setAddress(String address) {
        this.address = address;
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
     * Getter for the User's password.
     * @return The User's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for the User's current email address.
     * @return The User's current email address.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter of the User's current phone number.
     * @return The User's current phone number.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Getter of the User's current address.
     * @return The User's current address.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter for the type of User. Used in the database.
     * @return The type of User in int format.
     */
    public int getType() {
        return this.userType;
    }

    /**
     * Checker for whether this User object equals another.
     * Used in removal from the User ArrayList.
     * @param o The other User.
     * @return Whether this User equals the other User.
     */
    @Override
    public boolean equals(Object o) {
        return ((o instanceof User) && (this.username.equals(((User) o).username)));
    }
}
