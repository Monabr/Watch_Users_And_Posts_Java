package com.example.watchusersandpostsjava.models;

public class Address {
    private String street;
    private String suite;
    private String city;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address(String street, String suite, String city) {
        this.street = street;
        this.suite = suite;
        this.city = city;
    }
}
