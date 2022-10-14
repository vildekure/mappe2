package com.example.mappe2s354592;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class EditContact extends Activity {
    ImageButton backButton;
    TextInputEditText innName, innTlf;
    Long contactId;

    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);

        dbHelper = new DBHandler(EditContact.this);
        db = dbHelper.getWritableDatabase();

        Intent triggerIntent = getIntent();
        contactId = triggerIntent.getLongExtra("contactId", -1);
        System.out.println("contactID:" + contactId);

        Contact contact = dbHelper.getOneContact(db, contactId);

        backButton = findViewById(R.id.button_back);
        innName = findViewById(R.id.name_text_field);
        innTlf = findViewById(R.id.tlf_text_field);

        innName.setText(contact.getName());
        innTlf.setText(contact.getTlf());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void editContact(View v) {
        Contact contact = new Contact();

        String editName = innName.getText().toString();
        String editTlf = innTlf.getText().toString();

        contact.set_ID(contactId);
        contact.setName(editName);
        contact.setTlf(editTlf);

        dbHelper.editContact(db, contact);
        finish();
    }

    public void deleteContact (View v) {
        dbHelper.deleteContact(db, contactId);
        finish();
    }

    /*
     dbHandler.DeleteFriendById(db, id);
        Friend deletedFriend = friendViewModel.getSelectedFriend().getValue();
        friendAdapter.remove(deletedFriend);
        navController.navigateUp();
     */
}