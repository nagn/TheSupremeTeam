package com.cs2340.smores.m4.model;

/**
 * Created by smores on 2/20/17.
 */

public class Customer extends User {

    public Customer(String realName, String username, String password, String email,
                        String phoneNumber, String address) {
        super(realName, username, password, email, phoneNumber, address);
    }

    @Override
    public String type() {
        return "Customer";
    }
}
