package com.cs2340.smores.m4.model;

import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The model class containing all hierarchical data for the app.
 */

public class Model {

    public static ArrayList<User> users;
    public static User user;

//    static {
//        users = new ArrayList<>();
//        users.add(new User("George P. Burdell", "user", "pass", 3));
//        users.add(new User("Samuel Mohr", "smores", "wowee", 3));
//        users.add(new User ("Yi", "Yi", "123456", 2));
//        users.add(new User ("May", "May", "123456", 1));
//    }

    public static void setUser(User user) {
        Model.user = user;
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

    public static boolean save(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for (User u : users) {
                writer.println(u.getRealName() + "," + u.getUsername()
                        + "," + u.getPassword() + "," + u.userTypeToInt());
            }
            writer.close();
            return true;
        } catch (FileNotFoundException fnfe) {
            return false;
        }
    }

    public static boolean load(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String[] info;
            while (line != null) {
                info = line.split(",");
                users.add(new User(info[0], info[1], info[2], Integer.parseInt(info[3])));
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            return false;
        } catch (IOException fnfe) {
            return false;
        }
    }
}