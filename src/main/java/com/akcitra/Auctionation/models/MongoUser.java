package com.akcitra.Auctionation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class MongoUser {
    @Id
    private ObjectId _id;

    private String name;
    private String username;

    @JsonIgnore
    private String password;

    public MongoUser() {}
    public MongoUser(ObjectId _id, String name,String username, String password) {
        this._id = _id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void set_id(ObjectId _id) { this._id = _id; } //Set Id
    public String get_id() { return this._id.toHexString(); } //Get Id

    public void setPassword(String password) { this.password = password; } // Set Password
    public String getPassword() { return password; } //Get Password

    public void setUsername(String username) { this.username = username; } //Set Username
    public String getUsername() { return username; } //Get Username


}
