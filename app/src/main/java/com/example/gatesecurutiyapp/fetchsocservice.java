package com.example.gatesecurutiyapp;

public class fetchsocservice
{
    String plumber;
    String cleaner;
    String electricity;
    String housemaid;
    String watersupplier;
    String wifiprovider;

    public fetchsocservice(){}


    public fetchsocservice(String plumber, String cleaner, String electricity, String housemaid, String watersupplier, String wifiprovider) {
        this.plumber = plumber;
        this.cleaner = cleaner;
        this.electricity = electricity;
        this.housemaid = housemaid;
        this.watersupplier = watersupplier;
        this.wifiprovider = wifiprovider;
    }

    public String getPlumber() {
        return plumber;
    }

    public void setPlumber(String plumber) {
        this.plumber = plumber;
    }

    public String getCleaner() {
        return cleaner;
    }

    public void setCleaner(String cleaner) {
        this.cleaner = cleaner;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getHousemaid() {
        return housemaid;
    }

    public void setHousemaid(String housemaid) {
        this.housemaid = housemaid;
    }

    public String getWatersupplier() {
        return watersupplier;
    }

    public void setWatersupplier(String watersupplier) {
        this.watersupplier = watersupplier;
    }

    public String getWifiprovider() {
        return wifiprovider;
    }

    public void setWifiprovider(String wifiprovider) {
        this.wifiprovider = wifiprovider;
    }
}

