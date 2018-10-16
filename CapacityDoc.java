package com.example.farahreza.demo;

public class CapacityDoc {
    String name,date,capacity,curcapacity;

    public CapacityDoc() {
    }

    public CapacityDoc(String name, String date, String capacity,String curcapacity) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.curcapacity=curcapacity;
    }

    public String getCurcapacity() {
        return curcapacity;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCapacity() {
        return capacity;
    }
}
