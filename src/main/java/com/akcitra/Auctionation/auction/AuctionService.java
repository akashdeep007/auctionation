package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.Auction;
import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.requests.AuctionCreateRequest;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.user.UserRepository;
import com.akcitra.Auctionation.user.UserService;
import com.akcitra.Auctionation.util.JwtUtils;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class AuctionService {

    @Autowired JwtUtils jwtUtils;
    @Autowired UserRepository userRepository;
    @Autowired BidRepository bidRepository;
    @Autowired AuctionRepository auctionRepository;


    public ResponseEntity<ResponseObject> addBid(String token, String roomId, Bid bid) throws ExecutionException, InterruptedException {
        //Sets up Firestore and Initializes 2 Bids Collection Reference.    auction -> {roomId} -> bids
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);
        CollectionReference bidsRef = auctionReference.collection("bids");


        String username = jwtUtils.extractUsername(token.substring(7)); //Gets username from JWT Token
        Auction auction = auctionRepository.findBy_id(roomId); //Gets AuctionData from MongoDB
        AucUser aucUser = userRepository.findByUsername(username); //Gets UserData from MongoDB

        //If the user is has less money
        if(bid.getBid() > aucUser.getWallet())
            return ResponseEntity.status(500).body(new ResponseObject(500, new ResponseData("Not enough money.")));

        //If the bid is expired
//        if(auction.getEndTime() < System.currentTimeMillis()){
//            Bid highestBid = (Bid) bidsRef.orderBy("price").limit(1).get().get().toObjects(Bid.class);
//            aucUser.setWallet(aucUser.getWallet() - bid.getBid());
//            bidRepository.save(bid);
//            return ResponseEntity.status(500).body(new ResponseObject(500, new ResponseData("Expired")));
//        }
        bid.setUserId(aucUser.get_id());
        bidsRef.document().set(bid); //Adds Bid in the Firebase

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Bid Added")));
    }




    public ResponseEntity<ResponseObject> createAuction(AuctionCreateRequest auctionCreateRequest){
        //Auction should be created on MongoDB
        Auction newAuction = new Auction(
                auctionCreateRequest.getAuctionTitle(),
                auctionCreateRequest.getStartTime(),
                auctionCreateRequest.getEndTime(),
                auctionCreateRequest.getParticipants()
        );
        auctionRepository.save(newAuction);
        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Auction Created")));
    }





    public ResponseEntity<ResponseObject> getRoom(String token, String room_name) throws ExecutionException, InterruptedException {
        //Checks if there is an username in the jwt token
        String username = jwtUtils.extractUsername(token.substring(7));
        if(username == null) return ResponseEntity.status(404).body(new ResponseObject(404, new ResponseData("User not Found")));

        //Fetches the Auction
        Auction auction = auctionRepository.findByAuctionName((room_name));
        System.out.println(auction.getAuctionName());

        //Checks if the user is in the invitee list.
        Boolean doesExist = false;
        ArrayList<String> participants = auction.getParticipants();
        for(String i: participants){
            if(i == username){
                doesExist = true;
                break;
            }
        }

        if(!doesExist){
            ResponseEntity.status(500).body(new ResponseObject(69, new ResponseData("Not allowed in this room")));
        }

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData(auction.get_id().toString())));
    }


}
