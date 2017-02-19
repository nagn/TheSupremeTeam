package com.cs2340.smores.m4.model;

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


    private static final Model instance = new Model();
    public static Model getInstance() { return instance; }

    private ArrayList<User> users;
    private User user;

    private Model() {
        users = new ArrayList<>();
        addUser(new User("George P. Burdell", "user", "pass", 3));
        addUser(new User("Samuel Mohr", "smores", "wowee", 3));
        addUser(new User ("Yi", "Yi", "123456", 2));
        addUser(new User ("May", "May", "123456", 1));
    }

    public User checkLogin(String username, String password) {
        for (int i = 0; i < this.users.size(); i++) {
            if (this.users.get(i).unlock(username, password)) {
                return this.users.get(i);
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean exists(String username) {
        for (User u : this.users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean toFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            for (User u : this.users) {
                writer.println(u.getRealName() + "," + u.getUsername()
                        + "," + u.getPassword(u) + "," + u.userTypeToInt());
            }
            writer.close();
            return true;
        } catch (FileNotFoundException fnfe) {
            return false;
        }
    }

    public boolean load(String fileName) {
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