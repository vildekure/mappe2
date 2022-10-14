package com.example.mappe2s354592.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mappe2s354592.AdapterAppointment;
import com.example.mappe2s354592.AdapterContact;
import com.example.mappe2s354592.AddAppointment;
import com.example.mappe2s354592.AddContact;
import com.example.mappe2s354592.EditAppointment;
import com.example.mappe2s354592.MainActivity;
import com.example.mappe2s354592.Models.Appointment;
import com.example.mappe2s354592.SetPreferencesActivity;
import com.example.mappe2s354592.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    Button addAppointment;
    ImageButton settingsButton;
    MainActivity mainActivity;
    ListView listAppointments;

    AdapterAppointment adapterAppointment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addAppointment = binding.addAppointment;
        settingsButton = binding.settingButton;

        listAppointments = binding.listAppointments;

        mainActivity = (MainActivity) getActivity();

        addAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addAppointment = new Intent(getActivity(), AddAppointment.class);
                startActivity(addAppointment);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetPreferencesActivity.class);
                startActivity(intent);
            }
        });

        listAppointments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Appointment appointment = adapterAppointment.getItem(position);
                Intent toEditApp = new Intent(getActivity(), EditAppointment.class);
                toEditApp.putExtra("appointmentId", appointment._ID);
                startActivity(toEditApp);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterAppointment = mainActivity.getAppointmentAdapter();

        listAppointments.setAdapter(adapterAppointment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}