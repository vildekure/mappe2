package com.example.mappe2s354592.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mappe2s354592.AdapterContact;

public class HomeViewModel extends ViewModel {

    // private final MutableLiveData<String> mText;
    private final MutableLiveData<AdapterContact> contactAdapter;

    public HomeViewModel() {
        contactAdapter = new MutableLiveData<>();

        /* mText = new MutableLiveData<>();
        mText.setValue("This is home fragment"); */
    }

    public void setContactAdapter(AdapterContact newAdapter) {
        contactAdapter.setValue(newAdapter);
    }

    public LiveData<AdapterContact> getContactAdapter() {
        return contactAdapter;
    }
}