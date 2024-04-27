package com.akcitra.Auctionation.models;

public class Participant {
    private String username;
    private String name;

    public Participant(){}

    public Participant(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public Participant(String username) {
        this.username = username;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
