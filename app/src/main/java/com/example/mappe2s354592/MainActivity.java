package com.example.mappe2s354592;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;
import com.example.mappe2s354592.ui.dashboard.DashboardViewModel;
import com.example.mappe2s354592.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.mappe2s354592.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String CHANNEL_ID = "Avtale";

    ArrayList<Contact> listContact;
    AdapterContact contactAdapter;
    ArrayList<Appointment> listAppointment;
    AdapterAppointment appointmentAdapter;

    DBHandler dbHelper;
    SQLiteDatabase db;

    HomeViewModel contactViewModel;
    DashboardViewModel appointmentViewModel;

    public ArrayList<Contact> getContacts() {
        return dbHelper.getAllContacts(db);
    }
    public ArrayList<Appointment> getAppointments() { return dbHelper.getAllAppointments(db); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.refrences, false);

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

        BroadcastReceiver myBroadcastReceiver = new MinBroadcastReciever();
        IntentFilter filter = new IntentFilter("com.example.service.MITTSIGNAL");
        filter.addAction("com.example.service.MITTSIGNAL");
        this.registerReceiver(myBroadcastReceiver, filter);
        createNotificationChannel();

        dbHelper = new DBHandler(MainActivity.this);
        db = dbHelper.getWritableDatabase();

        listContact = getContacts();
        contactAdapter = new AdapterContact(this, android.R.layout.simple_list_item_1, listContact);
        contactViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        contactViewModel .setContactAdapter(contactAdapter);

        listAppointment = getAppointments();
        appointmentAdapter = new AdapterAppointment(this, android.R.layout.simple_list_item_1, listAppointment);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                stoppService();
                startService();
            }
        });
    }

    public AdapterContact getContactAdapter() {
        return contactAdapter;
    }

    public AdapterAppointment getAppointmentAdapter() {
        return appointmentAdapter;
    }

    public void startService() {
        Intent intent = new Intent();
        intent.setAction("com.example.service.MITTSIGNAL");
        sendBroadcast(intent);
    }

    public void stoppService () {
        Intent i = new Intent(MainActivity.this, MinSendService.class);
        PendingIntent pintent = PendingIntent.getService(MainActivity.this, 0, i, 0);
        AlarmManager alarm =
                (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(alarm != null) {
            alarm.cancel(pintent);
        }
    }

    private void createNotificationChannel() {
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new
                NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
