package com.example.farahreza.demo;

import java.io.Serializable;

public class AppointmentInfo implements Serializable{

    String name,hospital,location,date,time,docname,uid,phone;

    public AppointmentInfo() {
    }

    public AppointmentInfo(String name, String hospital, String location, String date, String time, String docname, String uid,String phone) {
        this.name = name;
        this.hospital = hospital;
        this.location = location;
        this.date = date;
        this.time = time;
        this.docname = docname;
        this.uid = uid;
        this.phone = phone;

    }

    /*public AppointmentInfo(String name, String hospital, String location, String date, String time, String docname, String uid) {
        this.name = name;
        this.hospital = hospital;
        this.location = location;
        this.date = date;
        this.time = time;
        this.docname = docname;
        this.uid = uid;
    }*/

    @Override
    public String toString() {
        return name;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }
}
