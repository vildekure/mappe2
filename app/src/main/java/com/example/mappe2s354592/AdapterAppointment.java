package com.example.mappe2s354592;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mappe2s354592.Models.Appointment;

import java.util.ArrayList;

// med hjelp fra: https://stackoverflow.com/a/15298021
public class AdapterAppointment extends ArrayAdapter<Appointment> {
    private Activity activity;
    private ArrayList<Appointment> appointmentList;
    private static LayoutInflater inflater = null;

    public AdapterAppointment (Activity activity, int textViewResourceId,ArrayList<Appointment> appointmentList) {
        super(activity, textViewResourceId, appointmentList);
        try {
            this.activity = activity;
            this.appointmentList = appointmentList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return appointmentList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final AdapterAppointment.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(android.R.layout.simple_list_item_1, null);
                holder = new AdapterAppointment.ViewHolder();

                holder.display_name = (TextView) vi.findViewById(android.R.id.text1);


                vi.setTag(holder);
            } else {
                holder = (AdapterAppointment.ViewHolder) vi.getTag();
            }


            Appointment appointment = appointmentList.get(position);
            holder.display_name.setText(appointment.getDate() + " " + appointment.getTime() + " " + appointment.getLocation());


        } catch (Exception e) {


        }
        return vi;
    }
}