package com.example.mappe2s354592;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddAppointment extends Activity {
    ImageButton backButton;
    TextInputEditText innDate, innTime, innLoc, innMssg;
    Spinner spinnerContacts;
    AdapterContact contactAdapter;
    DBHandler dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addappointment);

        dbHelper = new DBHandler(AddAppointment.this);
        db = dbHelper.getWritableDatabase();
        backButton = findViewById(R.id.button_back);
        innDate = findViewById(R.id.date_text_field);
        innTime = findViewById(R.id.time_text_field);
        innLoc = findViewById(R.id.loc_text_field);
        innMssg = findViewById(R.id.message_text_field);
        spinnerContacts = findViewById(R.id.spinner_contacts);

        ArrayList<Contact> listContact = dbHelper.getAllContacts(db);
        contactAdapter = new AdapterContact(this, android.R.layout.simple_list_item_1, listContact);
        spinnerContacts.setAdapter(contactAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void addAppointment(View v) {
        String date = innDate.getText().toString();
        String time = innTime.getText().toString();
        String location = innLoc.getText().toString();
        String message = innMssg.getText().toString();
        Contact contactSpinner = (Contact) spinnerContacts.getSelectedItem();
        long contactId = contactSpinner.get_ID();
        Appointment appointment = new Appointment(date, time, location, message, contactId);
        System.out.println("Dette er en avtale for: " + contactId);
        dbHelper.addAppointment(db, appointment);
        finish();
    }
}
