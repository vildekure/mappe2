package com.example.mappe2s354592;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;

import java.util.ArrayList;
import java.util.List;

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
    static String APPOINTMENT_KEY_MESSAGE = "Message";
    static String APPOINTMENT_KEY_CONTACT_ID = "ContactId";

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
                + APPOINTMENT_KEY_LOCATION + " TEXT,"
                + APPOINTMENT_KEY_MESSAGE + " TEXT,"
                + APPOINTMENT_KEY_CONTACT_ID + " TEXT,"
                + " FOREIGN KEY (" + APPOINTMENT_KEY_CONTACT_ID +
                ") REFERENCES " + TABLE_CONTACTS + "(" + CONTACT_KEY_ID +"));";
        db.execSQL(CREATE_TABLE_APPOINTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(SQLiteDatabase db, Contact contact) {
        ContentValues values = new ContentValues();
        values.put(CONTACT_KEY_NAME, contact.getName());
        values.put(CONTACT_PH_NO, contact.getTlf());
        db.insert(TABLE_CONTACTS, null, values);
    }

    public ArrayList<Contact> getAllContacts(SQLiteDatabase db) {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.set_ID(cursor.getLong(0));
                contact.setName(cursor.getString(1));
                contact.setTlf(cursor.getString(2));
                contactList.add(contact);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return contactList;
    }

    public int editContact(SQLiteDatabase db, Contact contact) {
        ContentValues values = new ContentValues();
        values.put(CONTACT_KEY_NAME, contact.getName());
        values.put(CONTACT_PH_NO, contact.getTlf());
        int changed = db.update(TABLE_CONTACTS, values, CONTACT_KEY_ID + " =? ",
                new String[]{String.valueOf(contact.get_ID())});
        return changed;
    }

    public void deleteContact(SQLiteDatabase db, Long id) {
        db.delete(TABLE_CONTACTS, CONTACT_KEY_ID + " =? ",
                new String[]{Long.toString(id)});
    }

    public void addAppointment(SQLiteDatabase db, Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(APPOINTMENT_KEY_DATE, appointment.getDate());
        values.put(APPOINTMENT_KEY_TIME, appointment.getTime());
        values.put(APPOINTMENT_KEY_LOCATION, appointment.getLocation());
        values.put(APPOINTMENT_KEY_MESSAGE, appointment.getMessage());
        values.put(APPOINTMENT_KEY_CONTACT_ID, appointment.getContactId());
        db.insert(TABLE_APPOINTMENTS, null, values);
    }

    public ArrayList<Appointment> getAllAppointments(SQLiteDatabase db) {
        ArrayList<Appointment> appointmentsList = new ArrayList<Appointment>();
        String selectQuery = "SELECT * FROM " + TABLE_APPOINTMENTS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment();
                appointment.set_ID(cursor.getLong(0));
                appointment.setDate(cursor.getString(1));
                appointment.setTime(cursor.getString(2));
                appointment.setLocation(cursor.getString(3));
                appointment.setMessage(cursor.getString(4));
                appointment.setContactId(cursor.getLong(5));
                appointmentsList.add(appointment);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return appointmentsList;
    }


    public void deleteAppointment(SQLiteDatabase db, Long id) {
        db.delete(TABLE_APPOINTMENTS, APPOINTMENT_KEY_ID + " =? ",
                new String[]{Long.toString(id)});
    }

}
