package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Bid {
    @Id
    private Object _id;

    private Long timestamp;
    private ObjectId userId;
    private Double bid;
}
