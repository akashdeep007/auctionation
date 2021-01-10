package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.AucUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AucUser, String> {
    AucUser findByUsername(String username);
    AucUser findBy_id(String _id);
}
