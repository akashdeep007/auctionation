package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Auction;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionRepository extends MongoRepository<Auction, String> {
    Auction findBy_id(String _id);
    Auction findByAuctionName(String auctionName);
}
