package com.akcitra.Auctionation.models;

import org.json.JSONObject;

public class ResponseObject {
    private Integer code;
    private String message;
    private JSONObject data;

    public ResponseObject(String message, JSONObject data) {
        this.code = 69;
        this.message = message;
        this.data = data;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public ResponseObject(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseObject(String message){
        this.code = 69;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
