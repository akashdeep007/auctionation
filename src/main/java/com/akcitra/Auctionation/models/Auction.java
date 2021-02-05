package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Auction {
    @Id
    private ObjectId _id;

    private String auctionName;
    private Double currBid;
    private ArrayList<Bid> bidHistory;
    private Long endTime;
    private Long startTime;
    private ArrayList<String> participants;

    public Auction(String auctionName, Long startTime, Long endTime, ArrayList<String> participants) {
        this.auctionName = auctionName;
        this.endTime = endTime;
        this.startTime = startTime;
        this.participants = participants;
    }

    public Auction(ObjectId _id, String auctionName, Double currBid, ArrayList<Bid> bidHistory, Long endTime) {
        this._id = _id;
        this.auctionName = auctionName;
        this.currBid = currBid;
        this.bidHistory = bidHistory;
        this.endTime = endTime;
    }

    public Auction() { }

    public ArrayList<String> getParticipants() { return participants; }
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    public String getAuctionName() { return auctionName; }
    public void setAuctionName(String auctionName) { this.auctionName = auctionName; }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }

    public Double getCurrBid() { return currBid; }
    public void setCurrBid(Double currBid) { this.currBid = currBid; }

    public ArrayList<Bid> getBidHistory() { return bidHistory; }
    public void setBidHistory(ArrayList<Bid> bidHistory) { this.bidHistory = bidHistory; }

    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public Long getEndTime() { return endTime; }
    public void setEndTime(Long endTime) { this.endTime = endTime; }

}
