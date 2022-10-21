package com.example.mappe2s354592;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReciever extends BroadcastReceiver {

    public void MinBroadcastReceiver() {}

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MinPeriodiskService.class);
        context.startService(i);

        System.out.println("HALLO fra MinBroadcastReciever");
    }
}
