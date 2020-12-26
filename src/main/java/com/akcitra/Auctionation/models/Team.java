package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

public class Team {
    @Id
    private ObjectId _id;
    private final String owner;
    private ArrayList<Player> players;

    public Team(ObjectId _id, String owner, ArrayList<Player> players) {
        this._id = _id;
        this.owner = owner;
        this.players = players;
    }


}
