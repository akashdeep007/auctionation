package com.akcitra.Auctionation.models.requests;

import com.akcitra.Auctionation.models.Participant;

import java.util.ArrayList;

public class AuctionCreateRequest {
    private String auctionName;
    private Long startTime;
    private ArrayList<Participant> participants;
    private String organizerName;

    public AuctionCreateRequest(String auctionTitle, Long startTime, ArrayList<Participant> participants, String organizerName) {
        this.auctionName = auctionTitle;
        this.startTime = startTime;
        this.participants = participants;
        this.organizerName = organizerName;
    }

    public ArrayList<Participant> getParticipants() { return participants; }
    public void setParticipants(ArrayList<Participant> participants) { this.participants = participants; }

    public Long getStartTime() { return startTime; }
    public void setStartTime(Long startTime) { this.startTime = startTime; }

    public String getAuctionName() { return auctionName; }
    public void setAuctionName(String auctionTitle) { this.auctionName = auctionTitle; }

    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String item_id) { this.organizerName = item_id; }
}
