package com.example.mappe2s354592.Models;

public class Contact {
    public long _ID;
    public String name;
    public String tlf;

    public Contact () {
    }

    public Contact(String name, String tlf) {
        this.name = name;
        this.tlf = tlf;
    }

    public Contact(long _ID, String name, String tlf) {
        this._ID = _ID;
        this.name = name;
        this.tlf = tlf;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
}
