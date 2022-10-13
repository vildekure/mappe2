package com.example.mappe2s354592;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mappe2s354592.Models.Contact;

import java.util.ArrayList;

// med hjelp fra: https://stackoverflow.com/a/15298021
public class AdapterContact extends ArrayAdapter<Contact> {
    private Activity activity;
    private ArrayList<Contact> contactList;
    private static LayoutInflater inflater = null;

    public AdapterContact (Activity activity, int textViewResourceId,ArrayList<Contact> contactList) {
        super(activity, textViewResourceId, contactList);
        try {
            this.activity = activity;
            this.contactList = contactList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public int getCount() {
        return contactList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView display_name;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final AdapterContact.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(android.R.layout.simple_list_item_1, null);
                holder = new AdapterContact.ViewHolder();

                holder.display_name = (TextView) vi.findViewById(android.R.id.text1);


                vi.setTag(holder);
            } else {
                holder = (AdapterContact.ViewHolder) vi.getTag();
            }


            Contact contact = contactList.get(position);
            holder.display_name.setText(contact.getName() + " " + contact.getTlf());


        } catch (Exception e) {


        }
        return vi;
    }
}