package com.example.farahreza.demo;

public class EmergencyJava {

    String name, nmbr,location;

    public EmergencyJava() {
    }

    public EmergencyJava(String name, String nmbr, String location) {
        this.name = name;
        this.nmbr = nmbr;
        this.location = location;
    }

    @Override
    public String toString() {
        return  name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNmbr() {
        return nmbr;
    }

    public void setNmbr(String nmbr) {
        this.nmbr = nmbr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
