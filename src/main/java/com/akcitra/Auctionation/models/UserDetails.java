package com.akcitra.Auctionation.models;

import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;

public class UserDetails {
    @Id
    private ObjectId _id;
    private String name;
    private Integer age;
    private String team_name;
    private Team team;

    public UserDetails(String name, Integer age, String team_name, Team team) {
        this.name = name;
        this.age = age;
        this.team_name = team_name;
        this.team = team;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
