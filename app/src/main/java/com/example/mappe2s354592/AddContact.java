package com.example.mappe2s354592;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class AddContact extends Activity {
    ImageButton backButton;
    TextInputEditText innName, innTlf;
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        dbHelper = new DBHandler(AddContact.this);
        db = dbHelper.getWritableDatabase();
        backButton = findViewById(R.id.back_button);
        innName = findViewById(R.id.name_text_field);
        innTlf =findViewById(R.id.tlf_text_field);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

     public void addContact(View v) {
        Contact contact = new Contact(innName.getText().toString(), innTlf.getText().toString());
        dbHelper.addContact(db, contact);
    }

}
