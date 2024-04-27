package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Auction;
import java.util.List;

import com.akcitra.Auctionation.models.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AuctionRepository extends MongoRepository<Auction, String> {
    Auction findBy_id(String _id);
    Auction findByAuctionName(String auctionName);

    List<Auction> findByParticipants_Username(String username);
}
