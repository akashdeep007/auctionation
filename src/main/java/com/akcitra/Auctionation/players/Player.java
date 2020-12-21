package com.akcitra.Auctionation.players;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private ObjectId _id;
    private final String name;
    private final String team;
    private final Double rating;

    public Player(ObjectId _id, String name, String team, Double rating) {
        this._id = _id;
        this.name = name;
        this.team = team;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) {  this._id = _id; }

    public String getTeam() {
        return team;
    }

    public Double getRating() {
        return rating;
    }
}
