package com.akcitra.Auctionation.players;

import com.akcitra.Auctionation.models.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerRepository repository;


    @RequestMapping( method = RequestMethod.GET )
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Player getPetById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPlayerById(@PathVariable("id") ObjectId id, @RequestBody Player player) {
        player.set_id(id);
        repository.save(player);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Player createPlayer(@RequestBody Player player) {
        player.set_id(ObjectId.get());
        repository.save(player);
        return player;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
