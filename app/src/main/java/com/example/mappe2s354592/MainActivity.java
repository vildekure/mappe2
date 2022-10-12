package com.example.mappe2s354592;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mappe2s354592.Models.Contact;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mappe2s354592.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TextInputEditText innName, innTlf;
    TextView listContacts;
    ArrayList<String> contactList = new ArrayList<>();
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        dbHelper = new DBHandler(this);
        db = dbHelper.getWritableDatabase();
        innName = findViewById(R.id.name_text_field);
        innTlf =findViewById(R.id.tlf_text_field);
        listContacts = findViewById(R.id.list_contatacts);
    }

    public void addContact(View v) {
        Contact contact = new Contact(innName.getText().toString(), innTlf.getText().toString());
        dbHelper.addContact(db, contact);
    }

    public void listContacts(View v) {
        String text = "";
        List<Contact> contacts = dbHelper.getAllContacts(db);
        for (Contact contact : contacts) {
            text += "Navn: " + contact.getName()
                    + "Telefon: " + contact.getTlf() + "\n";
        }
        listContacts.setText(text);
    }

}