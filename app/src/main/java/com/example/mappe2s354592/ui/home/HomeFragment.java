package com.example.mappe2s354592.ui.home;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mappe2s354592.AdapterContact;
import com.example.mappe2s354592.AddContact;
import com.example.mappe2s354592.DBHandler;
import com.example.mappe2s354592.EditContact;
import com.example.mappe2s354592.MainActivity;
import com.example.mappe2s354592.Models.Contact;
import com.example.mappe2s354592.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button addContact;
    MainActivity mainActivity;
    ListView listContacts;

    AdapterContact contactAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addContact = binding.addContact;
        listContacts = binding.listContacts;

        mainActivity = (MainActivity) getActivity();

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContact = new Intent(getActivity(), AddContact.class);
                startActivity(addContact);
            }
        });
        listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Contact contact = contactAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), EditContact.class);
                intent.putExtra("contactId", contact._ID);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        contactAdapter = mainActivity.getContactAdapter();

        listContacts.setAdapter(contactAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}