package com.example.mappe2s354592;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class EditContact extends Activity {
    ImageButton backButton;
    TextInputEditText innName, innTlf;

    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);

        dbHelper = new DBHandler(EditContact.this);
        db = dbHelper.getWritableDatabase();

        backButton = findViewById(R.id.button_back);
        innName = findViewById(R.id.name_text_field);
        innTlf = findViewById(R.id.tlf_text_field);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void editContact(View v) {
        Contact contact = new Contact();
        contact.setName(innName.getText().toString());
        contact.setTlf(innTlf.getText().toString());
        dbHelper.editContact(db, contact);
        finish();
    }

    public void deleteContact (View v) {
        Contact contact = new Contact();
        long contactId = contact.get_ID();
        dbHelper.deleteContact(db, contactId);
        finish();
    }
}