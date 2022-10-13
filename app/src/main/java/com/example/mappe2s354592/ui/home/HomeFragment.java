package com.example.mappe2s354592.ui.home;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mappe2s354592.AdapterContact;
import com.example.mappe2s354592.AddContact;
import com.example.mappe2s354592.DBHandler;
import com.example.mappe2s354592.MainActivity;
import com.example.mappe2s354592.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button addContact;
    MainActivity mainActivity;
    ListView listContacts;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addContact = binding.addContact;
        listContacts = binding.listContatacts;

        mainActivity = (MainActivity) getActivity();
        AdapterContact testAdapter = mainActivity.getContactAdapter();

        listContacts.setAdapter(mainActivity.getContactAdapter());

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContact = new Intent(getActivity(), AddContact.class);
                startActivity(addContact);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}