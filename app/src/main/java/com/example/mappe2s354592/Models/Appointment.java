package com.example.mappe2s354592.Models;

public class Appointment {
    public long _ID;
    public String date;
    public String time;
    public String location;

    public Appointment () {
    }

    public Appointment(String date, String time, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public Appointment(long _ID, String date, String time, String location) {
        this._ID = _ID;
        this.date = date;
        this.time = time;
        this.location = location;
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
}