package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.auction.AuctionRepository;
import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.Auction;
import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.Participant;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.models.responses.UserData;
import com.akcitra.Auctionation.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8080"})
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private AuctionRepository auctionRepository;

    @GetMapping
    public ResponseObject getUserDetails(@RequestHeader("Authorization") String token){
        String username = jwtUtils.extractUsername(token.substring(7));
        System.out.println(username);
        if(username == null) return new ResponseObject(404, new ResponseData("Token not Valid"));
        AucUser aucUser = userRepository.findByUsername(username);
        System.out.println(aucUser.getUsername());
        if(aucUser == null) return new ResponseObject(404, new ResponseData("User not found"));
        return new ResponseObject(69, new UserData("User Found", aucUser.getUsername(), aucUser.getName(), aucUser.getWallet()));
    }

    @PutMapping
    public AucUser setUserDetails(@RequestHeader("Authorization") String token, @RequestBody AucUser aucUser){
        userRepository.save(aucUser);
        return aucUser;
    }

    @GetMapping("/bids")
    public ResponseEntity<List<Bid>> getUserBids(@RequestHeader("Authorization") String token){
        return userService.getUserBids(token);
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> getUserAuctions(@RequestHeader("Authorization") String token){
        String username = jwtUtils.extractUsername(token.substring(7));
        return ResponseEntity.status(200).body(auctionRepository.findByParticipants_Username(username));
    }


}