package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.Player;
import com.akcitra.Auctionation.models.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {
    UserDetails findBy_id(ObjectId _id);

}
