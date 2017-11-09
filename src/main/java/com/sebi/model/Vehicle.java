package com.sebi.model;

public class Vehicle {

    private String regNo;
    private String make;
    private String colour;

    public Vehicle(String regNo, String make, String colour) {
        this.regNo = regNo;
        this.make = make;
        this.colour = colour;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
