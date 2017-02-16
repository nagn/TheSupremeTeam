package com.cs2340.smores.m4;

import java.util.ArrayList;

/**
 * The model class containing all hierarchical data for the app.
 */

class Model {


    private static final Model _instance = new Model();
    static Model getInstance() { return _instance; }

    private static ArrayList<User> users;
    private static User user;

    private Model() {
        users = new ArrayList<>();
        User firstUser = new User("George P. Burdell", "user", "pass");
        User manager = new User ("Yi", "Yi", "123456" );
        User worker = new User ("May", "May", "123456");
        firstUser.setAdmin("Alohamora!");
        manager.setManager(firstUser);
        worker.setWorker(manager);
        addUser(firstUser);
        addUser(manager);
        addUser(worker);
    }

    static User checkLogin(String givenUsername, String givenPassword) {
        for (int i = 0; i < users.size(); i++) {
            if (Model.users.get(i).unlock(givenUsername, givenPassword)) {
                return Model.users.get(i);
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return Model.users;
    }

    User getUser() {
        return Model.user;
    }

    void setUser(User user) {
        Model.user = user;
    }

    void addUser(User user) {
        users.add(user);
    }

    boolean inTheList (String user) {
        for (int i =0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }
}
