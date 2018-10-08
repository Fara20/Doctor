package com.example.farahreza.demo;

public class Ambulance {

    String name,contact;

    public Ambulance() {
    }

    public Ambulance(String name, String contact) {
        this.name = name;
        this.contact = contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
