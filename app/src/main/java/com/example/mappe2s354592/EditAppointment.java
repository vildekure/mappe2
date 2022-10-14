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

public class EditAppointment extends Activity {
    ImageButton backButton;
    TextInputEditText innDate, innTime, innLoc, innMssg;
    Button buttonEdit, buttonDelete;

    Spinner spinnerContacts;
    AdapterContact adapter;
    DBHandler dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editappointment);

        dbHelper = new DBHandler(EditAppointment.this);
        db = dbHelper.getWritableDatabase();

        Intent getAppointment = getIntent();
        Long appointmentId = getAppointment.getLongExtra("appointmentId", -1);

        backButton = findViewById(R.id.button_back);
        buttonEdit = findViewById(R.id.button_edit);
        buttonDelete = findViewById(R.id.button_delete);

        innDate = findViewById(R.id.date_text_field);
        innTime = findViewById(R.id.time_text_field);
        innLoc = findViewById(R.id.loc_text_field);
        innMssg = findViewById(R.id.message_text_field);


        spinnerContacts = findViewById(R.id.spinner_contacts);
        spinnerContacts.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void editAppointments(View v) {
        Appointment appointment = new Appointment();
        appointment.setDate(innDate.getText().toString());
        appointment.setTime(innTime.getText().toString());
        appointment.setLocation(innLoc.getText().toString());
        appointment.setMessage(innMssg.getText().toString());
        dbHelper.editAppointment(db, appointment);
        finish();
    }

    public void deleteAppointment (View v) {
        Appointment appointment = new Appointment();
        long appointId = appointment.get_ID();
        dbHelper.deleteAppointment(db, appointId);
        finish();
    }
}
