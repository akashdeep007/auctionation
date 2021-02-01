package com.akcitra.Auctionation.models.responses;

import java.util.ArrayList;

public class UserData extends ResponseData{
    private String username;
    private String name;
    private String email;
    private ArrayList<String> invitations;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public UserData(String name, String email, String message) {
        super(message);
        this.name = name;
        this.email = email;
    }

    public ArrayList<String> getInvitations() { return invitations; }
    public void setInvitations(ArrayList<String> invitations) { this.invitations = invitations; }

    public UserData(String name, String email, String username, String message) {
        super(message);
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
