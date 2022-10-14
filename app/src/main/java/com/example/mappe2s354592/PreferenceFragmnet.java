package com.example.mappe2s354592;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;

import androidx.preference.PreferenceFragmentCompat;

public class PreferenceFragmnet extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener,
        Preference.OnPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.refrences);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
    }
    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }
}
