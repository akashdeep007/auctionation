package com.akcitra.Auctionation.models.requests;

public class AddItemRequest {
    private String roomId;
    private String itemId;

    public AddItemRequest() { }

    public AddItemRequest(String roomId, String itemId) {
        this.roomId = roomId;
        this.itemId = itemId;
    }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
}
