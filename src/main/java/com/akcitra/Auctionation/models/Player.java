package com.akcitra.Auctionation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private ObjectId _id;
    private final String name;
    private final String team;
    private final Double rating;
    private String role;
    private String image_url;


    public Player(ObjectId _id, String name, String team, Double rating, String role) {
        this._id = _id;
        this.name = name;
        this.team = team;
        this.rating = rating;
        this.role = role;
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

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getImage_url() { return image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }
}
