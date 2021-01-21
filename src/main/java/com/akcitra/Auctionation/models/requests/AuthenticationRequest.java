package com.akcitra.Auctionation.models.requests;

public class AuthenticationRequest {
    private String username;

    public AuthenticationRequest(){}
    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    private String password;
}
