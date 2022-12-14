package com.example.mappe2s354592.Models;

public class Appointment {
    public long _ID;
    public String date;
    public String time;
    public String location;
    public String message;
    public long contactId;

    public Appointment () {
    }

    public Appointment(String date, String time, String location, String message, long contactId) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.message = message;
        this.contactId = contactId;
    }

    public Appointment(long _ID, String date, String time, String location, String message, long contactId) {
        this._ID = _ID;
        this.date = date;
        this.time = time;
        this.location = location;
        this.message = message;
        this.contactId = contactId;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}
