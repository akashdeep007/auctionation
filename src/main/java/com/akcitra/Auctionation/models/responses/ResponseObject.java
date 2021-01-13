package com.akcitra.Auctionation.models.responses;

import com.akcitra.Auctionation.models.responses.ResponseData;

public class ResponseObject {
    private Integer code;
    private ResponseData data;

    public ResponseObject(Integer code, ResponseData data) {
        this.code = code;
        this.data = data;
    }
    public ResponseObject(ResponseData data) {
        this.code = 69;
        this.data = data;
    }

    public ResponseObject(Integer code) {
        this.code = code;
    }

    public ResponseData getData() {
        return data;
    }
    public void setData(ResponseData data) {
        this.data = data;
    }


    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }



}
