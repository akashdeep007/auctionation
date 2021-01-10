package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Auction {
    @Id
    private ObjectId _id;

    private Double currBid;
    private ArrayList<Bid> bidHistory;
    private Player player;
    private Long endTime;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Double getCurrBid() {
        return currBid;
    }

    public void setCurrBid(Double currBid) {
        this.currBid = currBid;
    }

    public ArrayList<Bid> getBidHistory() {
        return bidHistory;
    }

    public void setBidHistory(ArrayList<Bid> bidHistory) {
        this.bidHistory = bidHistory;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Auction() { }
}
