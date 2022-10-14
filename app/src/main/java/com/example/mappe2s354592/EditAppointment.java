package com.example.mappe2s354592;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditAppointment extends Activity {
    ImageButton backButton;
    TextInputEditText innDate, innTime, innLoc, innMssg;
    Button buttonEdit, buttonDelete;
    Spinner spinnerContacts;
    Long appointmentId;

    AdapterContact adapterContact;
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editappointment);

        dbHelper = new DBHandler(EditAppointment.this);
        db = dbHelper.getWritableDatabase();

        Intent getAppointment = getIntent();
        appointmentId = getAppointment.getLongExtra("appointmentId", -1);

        Appointment appointment = dbHelper.getOneAppointment(db, appointmentId);

        backButton = findViewById(R.id.button_back);
        buttonEdit = findViewById(R.id.button_edit);
        buttonDelete = findViewById(R.id.button_delete);

        innDate = findViewById(R.id.date_text_field);
        innTime = findViewById(R.id.time_text_field);
        innLoc = findViewById(R.id.loc_text_field);
        innMssg = findViewById(R.id.message_text_field);

        innDate.setText(appointment.getDate());
        innTime.setText(appointment.getTime());
        innLoc.setText(appointment.getLocation());
        innMssg.setText(appointment.getMessage());

        spinnerContacts = findViewById(R.id.spinner_contacts);
        ArrayList<Contact> listContacts = dbHelper.getAllContacts(db);
        adapterContact = new AdapterContact(this, android.R.layout.simple_list_item_1, listContacts);
        spinnerContacts.setAdapter(adapterContact);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void editAppointments(View v) {
        Appointment appointment = new Appointment();

        String editDate = innDate.getText().toString();
        String editTime = innTime.getText().toString();
        String editLocation = innLoc.getText().toString();
        String editMessage = innMssg.getText().toString();

        Contact contactSpinner = (Contact) spinnerContacts.getSelectedItem();
        long spinnerId = contactSpinner.get_ID();

        appointment.set_ID(appointmentId);
        appointment.setDate(editDate);
        appointment.setTime(editTime);
        appointment.setLocation(editLocation);
        appointment.setMessage(editMessage);
        appointment.setContactId(spinnerId);

        dbHelper.editAppointment(db, appointment);
        finish();
    }

    public void deleteAppointment (View v) {
        dbHelper.deleteAppointment(db, appointmentId);
        finish();
    }
}
