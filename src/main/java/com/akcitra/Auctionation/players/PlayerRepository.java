package com.akcitra.Auctionation.players;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findBy_id(ObjectId _id);
}
