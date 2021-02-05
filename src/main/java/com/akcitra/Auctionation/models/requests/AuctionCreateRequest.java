package com.akcitra.Auctionation.models.requests;

import java.util.ArrayList;

public class AuctionCreateRequest {
    private String auctionTitle;
    private Long startTime;
    private Long endTime;
    private ArrayList<String> participants;
    private String item_id;

    public AuctionCreateRequest(String auctionTitle, Long startTime, Long endTime, ArrayList<String> participants, String item_id) {
        this.auctionTitle = auctionTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.item_id = item_id;
    }

    public ArrayList<String> getParticipants() { return participants; }
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public Long getEndTime() { return endTime; }
    public void setEndTime(Long endTime) { this.endTime = endTime; }

    public String getAuctionTitle() { return auctionTitle; }
    public void setAuctionTitle(String auctionTitle) { this.auctionTitle = auctionTitle; }

    public String getItem_id() { return item_id; }
    public void setItem_id(String item_id) { this.item_id = item_id; }
}
