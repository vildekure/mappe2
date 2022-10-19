package com.example.mappe2s354592;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class MinPeriodiskService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String time =  sharedPreferences.getString("time", "");
        int timeInt = Integer.parseInt(time);

        java.util.Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, timeInt);

        Intent i = new Intent(this, MinSendService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, i, 0);

        AlarmManager alarm =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 10 * 1000 , pintent);

        return super.onStartCommand(intent, flags, startId);}
}
