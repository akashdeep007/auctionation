package com.akcitra.Auctionation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collections;

public class AucUser {
    @Id
    private ObjectId _id;

    private String name;
    private String username;

    private String password;

    private String email;
    private Integer age;
    private String team_name;
    private Team team;
    private Double wallet;

    public AucUser(){ }

    public AucUser(ObjectId _id, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this._id = _id;
        this.email = email;
    }

    public Double getWallet() { return wallet; }
    public void setWallet(Double wallet) { this.wallet = wallet; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void set_id(ObjectId _id) { this._id = _id; } //Set Id
    public String get_id() { return this._id.toString(); } //Get Id

    public void setPassword(String password) { this.password = password; } // Set Password
    public String getPassword() { return password; } //Get Password

    public void setUsername(String username) { this.username = username; } //Set Username
    public String getUsername() { return username; } //Get Username

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) { this.age = age; }

    public String getTeam_name() {
        return team_name;
    }
    public void setTeam_name(String team_name) { this.team_name = team_name; }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }


}
