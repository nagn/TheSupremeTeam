package com.cs2340.smores.m4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database handler to store SourceReport data between app sessions.
 */

public class SourceReportDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sourceReportDatabase";
    private static final String TABLE_REPORTS = "reports";

    private static final String KEY_NUMBER = "reportNumber";
    private static final String KEY_REAL_NAME = "realName";
    private static final String KEY_DATE = "dateCreated";
    private static final String KEY_TYPE = "waterType";
    private static final String KEY_CONDITION = "condition";
    private static final String KEY_LOCATION = "location";

    /**
     * Constructor for a new Source Report Database Handler. Uses the overall context of the app.
     *
     * @param context The context of the application.
     */
    public SourceReportDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_NUMBER + " INTEGER PRIMARY KEY," + KEY_REAL_NAME + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_TYPE + " TEXT," + KEY_CONDITION + " TEXT,"
                + KEY_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
        onCreate(db);
    }

    /**
     * Adds a Source Report to the database.
     *
     * @param report The Source Report to add to the database.
     */
    void addReport(SourceReport report) {
        if (report != null) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NUMBER, report.getReportNumber());
            values.put(KEY_REAL_NAME, report.getName());
            values.put(KEY_DATE, report.getTimeCreated());
            values.put(KEY_TYPE, report.getWaterType());
            values.put(KEY_CONDITION, report.getCondition());
            values.put(KEY_LOCATION, report.getLocation());

            db.insert(TABLE_REPORTS, null, values);
            db.close();
        }
    }

    /**
     * Getter for all of the Source Reports currently in the system.
     *
     * @return An ArrayList of all of the Source Reports in the database.
     */
    public ArrayList<SourceReport> getQualityReports() {
        ArrayList<SourceReport> sourceReports = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int number;
            String name, timeCreated, type, condition, location;
            do {
                number = Integer.parseInt(cursor.getString(0));
                name = cursor.getString(1);
                timeCreated = cursor.getString(2);
                type = cursor.getString(3);
                condition = cursor.getString(4);
                location = cursor.getString(5);
                sourceReports.add(new SourceReport(number, name, timeCreated,
                        location, type, condition));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return sourceReports;
    }

    /**
     * Removes the Source Report with a matching report number from the database.
     *
     * @param report The Source Report to remove from the database.
     */
    void removeReport(SourceReport report) {
        if (report != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_REPORTS, KEY_NUMBER + " = ?",
                    new String[]{String.valueOf(report.getReportNumber())});
            db.close();
        }
    }
}
