package com.hspedu.chapter08.homeRentSys;

public class House {
    private int id;
    private String name;
    private String phone;
    private String address;
    private double rent;
    private String state;

    public House(int id, String name, String phone, String address, double rent, String state) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.rent = rent;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRent() {
        return this.rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.id + "\t\t" + this.name + "\t" + this.phone + "\t\t" + this.address + "\t" + this.rent + "\t" + this.state;
    }
}
