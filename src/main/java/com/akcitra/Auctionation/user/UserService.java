package com.akcitra.Auctionation.user;


import com.akcitra.Auctionation.auction.BidRepository;
import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired BidRepository bidRepository;
    @Autowired JwtUtils jwtUtils;

    public AucUser getUserDetails(String id){
        AucUser userDetails = userRepository.findBy_id(id);
        if(userDetails == null) return null;
        else return userDetails;
    }

    public AucUser setUserDetails(String id, AucUser aucUser){
        userRepository.save(aucUser);
        return aucUser;
    }

    public ResponseEntity<List<Bid>> getUserBids(String token){
        String username = jwtUtils.extractUsername(token.substring(7));
        List<Bid> bids = bidRepository.findAllByUsername(username);
        return ResponseEntity.status(200).body(bids);
    }
}
