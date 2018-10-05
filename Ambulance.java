package com.example.farahreza.demo;

public class Ambulance {

    String name,contact,type,email;

    public Ambulance() {
    }

    public Ambulance(String name, String contact,String email,String type) {
        this.name = name;
        this.contact = contact;
        this.email=email;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
