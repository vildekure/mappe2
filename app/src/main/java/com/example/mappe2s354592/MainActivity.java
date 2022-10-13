package com.example.mappe2s354592;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;
import com.example.mappe2s354592.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
    ArrayList<Contact> listContact;
    AdapterContact contactAdapter;
    DBHandler dbHelper;
    SQLiteDatabase db;
    HomeViewModel contactViewModel;

    public ArrayList<Contact> getContacts() {
        return dbHelper.getAllContacts(db);
    }
    public ArrayList<Appointment> getAppointments() { return dbHelper.getAllAppointments(db); }

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

        dbHelper = new DBHandler(MainActivity.this);
        db = dbHelper.getWritableDatabase();
        listContact = getContacts();
        contactAdapter = new AdapterContact(this, android.R.layout.simple_list_item_1, listContact);
        contactViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        contactViewModel .setContactAdapter(contactAdapter);
    }

    public AdapterContact getContactAdapter() {
        return contactAdapter;
    }
}