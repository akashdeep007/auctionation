package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Auction;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AuctionRepository extends MongoRepository<Auction, String> {
    Auction findBy_id(String _id);
    Auction findByAuctionName(String auctionName);
    List<Auction> findByParticipants(String username);
}
