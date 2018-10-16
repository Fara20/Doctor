package com.example.farahreza.demo;

public class DoctorInfo {

    String name,gender,spec,capacity,timeslot1,timeslot2,timeslot3,type;

    public DoctorInfo() {
    }




    public DoctorInfo(String name, String gender, String spec, String capacity, String timeslot1, String timeslot2, String timeslot3,String type) {
        this.name = name;
        this.gender = gender;
        this.spec = spec;
        this.type = type;
        this.capacity = capacity;
        this.timeslot1 = timeslot1;
        this.timeslot2 = timeslot2;
        this.timeslot3 = timeslot3;
    }


    @Override
    public String toString() {
        return name;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getTimeslot1() {
        return timeslot1;
    }

    public void setTimeslot1(String timeslot1) {
        this.timeslot1 = timeslot1;
    }

    public String getTimeslot2() {
        return timeslot2;
    }

    public void setTimeslot2(String timeslot2) {
        this.timeslot2 = timeslot2;
    }

    public String getTimeslot3() {
        return timeslot3;
    }

    public void setTimeslot3(String timeslot3) {
        this.timeslot3 = timeslot3;
    }
}
