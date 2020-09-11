package com.example.gad_project;

public class Person {
    private String name, country, badgeUrl;
    private int  value;

    public Person(String name, String country, String badgeUrl, int value){
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.value = value;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
