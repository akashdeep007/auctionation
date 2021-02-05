package com.akcitra.Auctionation.models.requests;

import java.util.ArrayList;

public class AuctionCreateRequest {
    private String auctionTitle;
    private Long startTime;
    private Long endTime;
    private ArrayList<String> participants;

    public AuctionCreateRequest(String auctionTitle, Long startTime, Long endTime, ArrayList<String> participants) {
        this.auctionTitle = auctionTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public ArrayList<String> getParticipants() { return participants; }
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public Long getEndTime() { return endTime; }
    public void setEndTime(Long endTime) { this.endTime = endTime; }

    public String getAuctionTitle() { return auctionTitle; }
    public void setAuctionTitle(String auctionTitle) { this.auctionTitle = auctionTitle; }
}
