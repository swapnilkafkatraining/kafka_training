package com.example.SimpleUserApp.model;

public class User {

    public User(Integer userId, String firstname, String lastname, String country) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
    }

    private Integer userId;
    private String firstname;
    private String lastname;
    private String country;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
