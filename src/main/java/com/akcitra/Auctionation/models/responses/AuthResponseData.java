package com.akcitra.Auctionation.models.responses;

import com.akcitra.Auctionation.models.responses.ResponseData;

public class AuthResponseData extends ResponseData {
    private String token;

    public AuthResponseData(String message, String token) {
        super(message);
        this.token = token;
    }

    public AuthResponseData(String message) {
        super(message);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
