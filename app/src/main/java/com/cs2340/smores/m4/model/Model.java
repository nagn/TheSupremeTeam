package com.cs2340.smores.m4.model;

import java.util.ArrayList;


/**
 * The model class containing all hierarchical data for the app.
 */

public class Model {

    public static ArrayList<User> users;
    public static User user;
    public static DatabaseHandler dbHandler;

//    users.add(new User("George P. Burdell", "user", "pass", 3));
//    users.add(new User("Samuel Mohr", "smores", "wowee", 3));
//    users.add(new User ("Yi", "Yi", "123456", 2));
//    users.add(new User ("May", "May", "123456", 1));


    public static void setUser(User user) {
        Model.user = user;
    }

    public static void addUser(User user) {
        users.add(user);
        dbHandler.addUser(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
        dbHandler.removeUser(user);
    }

    public static User checkLogin(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).unlock(username, password)) {
                return users.get(i);
            }
        }
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

    private static boolean exists(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}