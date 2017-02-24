package com.cs2340.smores.m4.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database handler to store PurityReport data between app sessions.
 */

public class PurityReportDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "purityReportDatabase";
    private static final String TABLE_REPORTS = "reports";

    private static final String KEY_NUMBER = "reportNumber";
    private static final String KEY_REAL_NAME = "realName";
    private static final String KEY_DATE = "dateCreated";
    private static final String KEY_CONDITION = "condition";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_VIRUS_PPM = "virusPPM";
    private static final String KEY_CONTAMINANT_PPM = "contaminantPPM";

    public PurityReportDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_NUMBER + " INTEGER PRIMARY KEY," + KEY_REAL_NAME + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_CONDITION + " TEXT,"
                + KEY_LONGITUDE + " DOUBLE," + KEY_LATITUDE + " DOUBLE,"
                + KEY_VIRUS_PPM + " INTEGER," + KEY_CONTAMINANT_PPM + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
        onCreate(db);
    }

    void addReport(PurityReport report) {
        if (report != null) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NUMBER, report.getReportNumber());
            values.put(KEY_REAL_NAME, report.getName());
            values.put(KEY_DATE, report.getTimeCreated());
            values.put(KEY_CONDITION, report.getCondition());
            values.put(KEY_LONGITUDE, report.getLongitude());
            values.put(KEY_LATITUDE, report.getLatitude());
            values.put(KEY_VIRUS_PPM, report.getVirusPPM());
            values.put(KEY_CONTAMINANT_PPM, report.getContaminantPPM());

            db.insert(TABLE_REPORTS, null, values);
            db.close();
        }
    }

    public ArrayList<PurityReport> getPurityReports() {
        ArrayList<PurityReport> purityReports = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int number;
            String name;
            String timeCreated;
            String condition;
            double longitude;
            double latitude;
            int virusPPM;
            int contaminantPPM;
            do {
                number = Integer.parseInt(cursor.getString(0));
                name = cursor.getString(1);
                timeCreated = cursor.getString(2);
                condition = cursor.getString(3);
                longitude = Double.parseDouble(cursor.getString(4));
                latitude = Double.parseDouble(cursor.getString(5));
                virusPPM = Integer.parseInt(cursor.getString(6));
                contaminantPPM = Integer.parseInt(cursor.getString(7));
                purityReports.add(new PurityReport(number, name, timeCreated, longitude,
                latitude, condition, virusPPM, contaminantPPM));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return purityReports;
    }

    void removeReport(PurityReport report) {
        if (report != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_REPORTS, KEY_NUMBER + " = ?",
                    new String[]{String.valueOf(report.getReportNumber())});
            db.close();
        }
    }
}
