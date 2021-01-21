package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Bid {
    @Id
    private ObjectId _id;
    private ObjectId userId;
    private String username;
    private Double bid;
    private Long timestamp;

    public Bid(ObjectId _id,Long timestamp, ObjectId userId, String username, Double bid) {
        this._id = _id;
        this.timestamp = timestamp;
        this.userId = userId;
        this.username = username;
        this.bid = bid;
    }

    public Bid(Long timestamp, ObjectId userId, String username, Double bid) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.username = username;
        this.bid = bid;
    }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }

    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    public ObjectId getUserId() { return userId; }
    public void setUserId(ObjectId userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Double getBid() { return bid; }
    public void setBid(Double bid) { this.bid = bid; }
}
