package com.akcitra.Auctionation.players;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository repository;


    @RequestMapping(value="/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }
    @RequestMapping(value="/players/{id}", method = RequestMethod.GET)
    public Player getPetById(@PathVariable("id") ObjectId id) {
        return repository.findBy_id(id);
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.PUT)
    public void modifyPlayerById(@PathVariable("id") ObjectId id, @RequestBody Player player) {
        player.set_id(id);
        repository.save(player);
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public Player createPlayer(@RequestBody Player player) {
        player.set_id(ObjectId.get());
        repository.save(player);
        return player;
    }

    @RequestMapping(value = "/players/{id}", method = RequestMethod.DELETE)
    public void deletePlayer(@PathVariable ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }
}
