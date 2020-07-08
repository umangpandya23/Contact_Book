package com.example.contactbook;

public class Contact {
    //Fields for Contact class
    private int id;
    private String name;
    private String phone;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String contactType;

    public Contact(int id, String name, String phone, String email, String streetAddress, String city,
                   String state, String zip, String contactType) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.contactType = contactType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

}
