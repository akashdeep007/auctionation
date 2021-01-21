package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.Player;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<Bid, String> {
    Bid findBy_id(ObjectId _id);

}
