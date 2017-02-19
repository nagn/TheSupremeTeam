package com.cs2340.smores.m4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

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

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY," + KEY_REAL_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT," + KEY_EMAIL + " TEXT,"
                + KEY_PHONE_NUMBER + " TEXT," + KEY_ADDRESS + " TEXT,"
                + KEY_USER_TYPE + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();;
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_REAL_NAME, user.getRealName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE_NUMBER, user.getPhoneNumber());
        values.put(KEY_ADDRESS, user.getAddress());
        values.put(KEY_USER_TYPE, user.userTypeToInt());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query(TABLE_USERS, new String[] { KEY_USERNAME, KEY_REAL_NAME,
                KEY_PASSWORD, KEY_EMAIL, KEY_PHONE_NUMBER, KEY_ADDRESS, KEY_USER_TYPE },
                KEY_USERNAME + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (c != null)
            c.moveToFirst();
        User user = new User(c.getString(1), c.getString(0), c.getString(2),
                Integer.parseInt(c.getString(3)), c.getString(4), c.getString(5), c.getString(6));
        c.close();
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            User user;
            do {
                user = new User(cursor.getString(1), cursor.getString(0), cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)), cursor.getString(4),
                        cursor.getString(5), cursor.getString(6));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return users;
    }

    public void removeUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_USERNAME + " = ?",
                new String[] { String.valueOf(user.getUsername()) });
        db.close();
    }

    public int getSize() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}