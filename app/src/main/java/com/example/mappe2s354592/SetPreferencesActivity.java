package com.example.mappe2s354592;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mappe2s354592.ui.notifications.NotificationsFragment;

public class SetPreferencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_container,new PreferenceFragmnet())
                .commit();
    }
}