package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Auction {
    @Id
    private ObjectId _id;

    private String auctionName;
    private String organizerName;
    private Player current_item;
    private ArrayList<Bid> bidHistory;
    private Long endTime;



    private Long startTime;
    private ArrayList<Participant> participants;
    private ArrayList<Player> items;

    public Auction() { }

    public Auction(String auctionName, Long startTime, ArrayList<Participant> participants, String organizerName) {
        this.auctionName = auctionName;
        this.startTime = startTime;
        this.organizerName = organizerName;
        this.participants = participants;
    }


    public ArrayList<Participant> getParticipants() { return participants; }
    public void setParticipants(ArrayList<Participant> participants) { this.participants = participants; }

    public String getAuctionName() { return auctionName; }
    public void setAuctionName(String auctionName) { this.auctionName = auctionName; }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }

    public Player getCurrent_item() { return current_item; }
    public void setCurrent_item(Player current_item) { this.current_item = current_item; }

    public ArrayList<Bid> getBidHistory() { return bidHistory; }
    public void setBidHistory(ArrayList<Bid> bidHistory) { this.bidHistory = bidHistory; }

    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public Long getEndTime() { return endTime; }
    public void setEndTime(Long endTime) { this.endTime = endTime; }

    public ArrayList<Player> getItems() { return items; }
    public void setItems(ArrayList<Player> items) { this.items = items; }

    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }

}
