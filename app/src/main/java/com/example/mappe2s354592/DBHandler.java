package com.example.mappe2s354592;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_CONTACTS = "Contacts";
    static String CONTACT_KEY_ID = "_ID";
    static String CONTACT_KEY_NAME = "Name";
    static String CONTACT_PH_NO = "Tlf";
    static String TABLE_APPOINTMENTS = "Appointments";
    static String APPOINTMENT_KEY_ID = "_ID";
    static String APPOINTMENT_KEY_DATE = "Date";
    static String APPOINTMENT_KEY_TIME = "Time";
    static String APPOINTMENT_KEY_LOCATION = "Location";
    static int DATABASE_VERSION = 3;
    static String DATABASE_NAME = "ContactAppointments";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CONTACTS = "CREATE TABLE "
                + TABLE_CONTACTS
                + "(" + CONTACT_KEY_ID + "INTEGER PRIMARY KEY,"
                + CONTACT_KEY_NAME + " TEXT, "
                + CONTACT_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_CONTACTS);

        String CREATE_TABLE_APPOINTMENTS = "CREATE TABLE "
                + TABLE_APPOINTMENTS
                + "(" + APPOINTMENT_KEY_ID + "INTEGER PRIMARY KEY,"
                + APPOINTMENT_KEY_DATE + " TEXT, "
                + APPOINTMENT_KEY_TIME + " TEXT, "
                + APPOINTMENT_KEY_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_APPOINTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
