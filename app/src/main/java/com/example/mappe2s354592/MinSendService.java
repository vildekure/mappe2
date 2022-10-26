package com.example.mappe2s354592;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.Models.Contact;

import java.util.ArrayList;
import java.util.Calendar;

public class MinSendService extends Service {
    DBHandler dbHelper;
    SQLiteDatabase db;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbHelper = new DBHandler(this);
        db = dbHelper.getWritableDatabase();

        ArrayList<Appointment> allAppointments = dbHelper.getAllAppointments(db);

        // System.out.println("Her er man i MinSendService!!");

        Calendar dato = Calendar.getInstance();

        int year = dato.get(Calendar.YEAR);
        String yearString = Integer.toString(year);

        int month = dato.get(Calendar.MONTH) + 1;
        String monthString = Integer.toString(month);

        int day = dato.get(Calendar.DAY_OF_MONTH);
        String dayString = Integer.toString(day);

        String todayDate = dayString + "." + monthString + "." + yearString;

        // System.out.println("Dato: " + todayDate);

        for (Appointment appointment : allAppointments) {
            String appointmentDate = appointment.getDate();
            Contact contact = dbHelper.getOneContact(db, appointment.getContactId());
            String phoneNr = contact.getTlf();

            if (todayDate.equals(appointmentDate)) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                String defaultMessage = sharedPreferences.getString("melding", "");
                String appMessage = appointment.getMessage();

                if (appMessage.isEmpty()) {
                    sendMessage(phoneNr, defaultMessage);
                }
                else {
                    sendMessage(phoneNr, appMessage);
                }

                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                Intent i = new Intent(this, MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);

                Notification notifikasjon = new NotificationCompat.Builder(this,"Avtale")
                        .setContentTitle("Reminder")
                        .setContentText("Husk avtalen din i dag")
                        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pIntent).build();
                notifikasjon.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(88, notifikasjon);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void sendMessage (String tlf, String mssg) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(tlf, null, mssg, null, null);

    }
}

