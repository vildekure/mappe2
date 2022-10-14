package com.example.mappe2s354592;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReciever extends BroadcastReceiver {

    public void MinBroadcastReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context.getApplicationContext(), "I BroadcastReceiver", Toast.LENGTH_SHORT).show();

        /*Intent i = new Intent(context, MinService.class);
        context.startService(i);*/

        Intent i = new Intent(context, MinPeriodiskService.class);
        context.startService(i);
    }
}
