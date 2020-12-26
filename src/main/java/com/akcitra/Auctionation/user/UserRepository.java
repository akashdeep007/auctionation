package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<MongoUser, String> {
    MongoUser findByUsername(String username);
}
