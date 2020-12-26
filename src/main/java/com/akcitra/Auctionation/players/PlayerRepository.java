package com.akcitra.Auctionation.players;

import com.akcitra.Auctionation.models.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findBy_id(ObjectId _id);
}
