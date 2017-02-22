package com.cs2340.smores.m4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database handler to store User data between app sessions.
 */
public class UserDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDatabase";
    private static final String TABLE_USERS = "users";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_REAL_NAME = "realName";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_USER_TYPE = "userType";

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY," + KEY_REAL_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_USER_TYPE + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_PHONE_NUMBER +
                " TEXT," + KEY_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_REAL_NAME, user.getRealName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE_NUMBER, user.getPhoneNumber());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_USER_TYPE, user.type());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            User user;
            String name;
            String username;
            String password;
            String email;
            String phoneNumber;
            String address;
            do {
                name = cursor.getString(1);
                username = cursor.getString(0);
                password = cursor.getString(2);
                email = cursor.getString(4);
                phoneNumber = cursor.getString(5);
                address = cursor.getString(6);
                switch (cursor.getString(3)) {
                    case "Admin":
                        user = new Admin(name, username, password, email, phoneNumber, address);
                        break;
                    case "Manager":
                        user = new Manager(name, username, password, email, phoneNumber, address);
                        break;
                    case "Worker":
                        user = new Worker(name, username, password, email, phoneNumber, address);
                        break;
                    default:
                        user = new Customer(name, username, password, email, phoneNumber, address);
                        break;
                }
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return users;
    }

    void removeUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(user.getUsername()) });
        db.close();
    }
}