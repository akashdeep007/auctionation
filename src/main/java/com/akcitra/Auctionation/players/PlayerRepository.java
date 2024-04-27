package com.akcitra.Auctionation.players;


import java.util.List;
import com.akcitra.Auctionation.models.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findBy_id(String _id);
    List<Player> findByOwnerId(String ownerId);
}
