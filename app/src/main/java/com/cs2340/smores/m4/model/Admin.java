package com.cs2340.smores.m4.model;

/**
 * Created by smores on 2/20/17.
 */

public class Admin extends Manager {

    public Admin(String realName, String username, String password, String email,
                    String phoneNumber, String address) {
        super(realName, username, password, email, phoneNumber, address);
    }

    @Override
    public String type() {
        return "Admin";
    }

    public void unlock(User user) {
        user.unlock(this);
    }
}
