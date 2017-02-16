package com.cs2340.smores.m4;

/**
 * User object for the project.
 *
 * @author Samuel Mohr
 * @version 1.0
 */
class User {

    private final String realName;
    private final String username;
    private String password;
    private int id;
    private boolean isWorker;
    private boolean isManager;
    private boolean isAdmin;
    private static int count = 0;

    /**
     * Constructor for a User.
     * @param realName The real name of the User.
     * @param username The username of the User.
     * @param password The password of the User.
     */
    User(String realName, String username, String password) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.id = ++User.count;
        this.isWorker = false;
        this.isManager = false;
        this.isAdmin = false;
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
     * Setter for the User's password. Requires the old password.
     * @param oldPassword The current password of the User.
     * @param newPassword The new password for the User to use.
     */
    boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    /**
     * Setter for whether the User is a Worker. Changeable by Admins only.
     * @param user The Admin account to change the account type with.
     * @return Whether the value has been updated.
     */
    boolean setWorker(User user) {
        if (user.isAdmin() || user.isManager()) {
            this.isWorker = true;
            return true;
        }
        return false;
    }

    /**
     * Setter for whether the User is a Manager. Changeable by Admins only.
     * @param user The Admin account to change the account type with.
     * @return Whether the value has been updated.
     */
    boolean setManager(User user) {
        if (user.isAdmin()) {
            this.isWorker = true;
            this.isManager = true;
            return true;
        }
        return false;
    }

    /**
     * Setter for whether the User is an Admin. As no account type trumps Admin,
     * a password is required to become an admin.
     * @param password The password required to become an Admin.
     * @return Whether the value has been updated.
     */
    boolean setAdmin(String password) {
        if (password.equals("Alohamora!")) {
            this.isWorker = true;
            this.isManager = true;
            this.isAdmin = true;
            return true;
        }
        return false;
    }

    /**
     * Getter for the User's real name.
     * @return The User's real name.
     */
    String getRealName() {
        return this.realName;
    }

    /**
     * Getter for the User's username.
     * @return The User's username.
     */
    String getUsername() {
        return this.username;
    }

    /**
     * Getter for the User's password.  Only accessible by Admins and the user themselves.
     * @param user The User accessing the data.
     * @return Either the User's password or a null String.
     */
    String getPassword(User user) {
        if ((user.isAdmin()) || (user.equals(this))) {
            return this.password;
        }
        return null;
    }

    int getId() {
        return this.id;
    }

    /**
     * Getter for whether the User is a Worker.
     * @return Whether the User is a Worker.
     */
    boolean isWorker() {
        return this.isWorker;
    }

    /**
     * Getter for whether the User is a Manager.
     * @return Whether the User is a Manager.
     */
    boolean isManager() {
        return this.isManager;
    }

    /**
     * Getter for whether the User is an Admin.
     * @return Whether the User is an Admin.
     */
    boolean isAdmin() {
        return this.isAdmin;
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