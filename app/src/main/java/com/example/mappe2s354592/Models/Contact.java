package com.example.mappe2s354592.Models;

public class Contact {
    public long _ID;
    public String navn;
    public String tlf;

    public Contact () {
    }

    public Contact(String navn, String tlf) {
        this.navn = navn;
        this.tlf = tlf;
    }

    public Contact(long _ID, String navn, String tlf) {
        this._ID = _ID;
        this.navn = navn;
        this.tlf = tlf;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
}
