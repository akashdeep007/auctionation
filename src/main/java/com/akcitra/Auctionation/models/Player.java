package com.akcitra.Auctionation.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Player {
    @Id
    private ObjectId _id;
    private String name;
    private String team;
    private Double rating;
    private String role;
    private String image_url;
    private String description;

    private String ownerId;
    private String ownerName;


    public Player(){}

    public Player(String name, String team, Double rating, String image_url, String description) {
        this.name = name;
        this.team = team;
        this.rating = rating;
        this.image_url = image_url;
        this.description = description;
        this.ownerId = null;
        this.ownerName = null;
    }

    public Player(ObjectId _id, String name, String team, Double rating, String role) {
        this._id = _id;
        this.name = name;
        this.team = team;
        this.rating = rating;
        this.role = role;
    }

    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getImage_url() { return image_url; }
    public void setImage_url(String image_url) { this.image_url = image_url; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}
