package com.springkafka.springkafkapojouserexmple.model;

public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String country;

    public User(Integer userId, String firstName, String lastName, String country) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public User() { }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
