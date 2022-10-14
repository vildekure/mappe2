package com.example.mappe2s354592;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MinSendService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.getString("melding", "");

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

        return super.onStartCommand(intent, flags, startId);
    }
}

