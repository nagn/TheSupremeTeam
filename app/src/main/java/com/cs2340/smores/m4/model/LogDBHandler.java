package com.cs2340.smores.m4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database handler to store log data between app sessions.
 */

public class LogDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "logDatabase";
    private static final String TABLE_LOGS = "logs";

    private static final String KEY_ID = "id";
    private static final String KEY_LOG = "log";

    /**
     * Constructor for a new log Database Handler. Uses the overall context of the app.
     *
     * @param context The context of the application.
     */
    public LogDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_LOGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOG + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGS);
        onCreate(db);
    }

    /**
     * Adds a new log message to the log database.
     *
     * @param id The index of the log.
     * @param log The log message for the given log entry.
     */
    void addLog(int id, String log) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_LOG, log);

        db.insert(TABLE_LOGS, null, values);
        db.close();
    }

    /**
     * Getter for an ArrayList of all of the logs entered up-to-date.
     *
     * @return an ArrayList of all of the current log entries.
     */
    public ArrayList<String> getLog() {
        ArrayList<String> logs = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                logs.add(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return logs;
    }
}
