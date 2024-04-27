package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.*;
import com.akcitra.Auctionation.models.requests.AuctionCreateRequest;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.players.PlayerRepository;
import com.akcitra.Auctionation.user.UserRepository;
import com.akcitra.Auctionation.util.JwtUtils;
import com.google.cloud.firestore.*;
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
    @Autowired AuctionRepository auctionRepository;
    @Autowired PlayerRepository playerRepository;


    public ResponseEntity<ResponseObject> addBid(String token, String roomId, Bid bid) throws ExecutionException, InterruptedException {
        //Sets up Firestore and Initializes 2 Bids Collection Reference.    auction -> {roomId} -> bids
        Firestore dbFirestore = FirestoreClient.getFirestore();
        System.out.println(roomId + " " + bid.getItemId());
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);
        CollectionReference bidsRef = auctionReference.collection(bid.getItemId());


        String username = jwtUtils.extractUsername(token.substring(7)); //Gets username from JWT Token
        Auction auction = auctionRepository.findBy_id(roomId); //Gets AuctionData from MongoDB
        AucUser aucUser = userRepository.findByUsername(username); //Gets UserData from MongoDB

        //If the user is has less money
        if(bid.getBid() > aucUser.getWallet())
            return ResponseEntity.status(500).body(new ResponseObject(500, new ResponseData("Not enough money.")));

        auctionReference.update("max_bid", bid.getBid());
        auctionReference.update("max_bid_user", bid.getUsername());
        bid.setUserId(aucUser.get_id());
        bid.setUsername(username);
        bid.setName(aucUser.getName());
        bidsRef.document().set(bid); //Adds Bid in the Firebase

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Bid Added")));
    }

    public ResponseEntity<ResponseObject> createAuction(AuctionCreateRequest auctionCreateRequest){
        Firestore dbFirestore = FirestoreClient.getFirestore();

        //Auction should be created on MongoDB
        Auction newAuction = new Auction(
                auctionCreateRequest.getAuctionName(),
                auctionCreateRequest.getStartTime(),
                auctionCreateRequest.getParticipants(),
                auctionCreateRequest.getOrganizerName()
        );
        auctionRepository.save(newAuction);
        newAuction = auctionRepository.findByAuctionName(auctionCreateRequest.getAuctionName());

        DocumentReference auctionReference = dbFirestore.collection("auction").document(newAuction.get_id().toString());
        auctionReference.set(newAuction);
        auctionReference.update("active", true);


        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Auction Created")));
    }

    public ResponseEntity<ResponseObject> getRoom(String token, String room_name) throws ExecutionException, InterruptedException {
        String username = jwtUtils.extractUsername(token.substring(7));
        if(username == null) return ResponseEntity.status(404).body(new ResponseObject(404, new ResponseData("User not Found")));

        //Fetches the Auction
        Auction auction = auctionRepository.findByAuctionName((room_name));
        if(auction == null) return ResponseEntity.status(500).body(new ResponseObject(500, new ResponseData("Not such Room exists.")));
        System.out.println(auction.getAuctionName());

        //Checks if the user is in the invitee list.
        Boolean doesExist = false;
        ArrayList<Participant> participants = auction.getParticipants();
        for(Participant i: participants){
            System.out.println(username + " " + i.getUsername());
            if(i.getUsername().equals(username)){
                doesExist = true;
                break;
            }
        }

        if(!doesExist){
            return ResponseEntity.status(500).body(new ResponseObject(69, new ResponseData("Not allowed in this room")));
        }

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData(auction.get_id().toString())));
    }

    public ResponseEntity<ResponseObject> endAuction(String token, String roomId){
        System.out.println(roomId + "Here");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);
        auctionReference.update("active", false);
        return ResponseEntity.status(200).body(new ResponseObject(200, new ResponseData("Auction Closed.")));
    }

    public ResponseEntity<ResponseObject> endRound(String token, String roomId, String itemId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);
        CollectionReference bidsRef = auctionReference.collection(itemId);

        QueryDocumentSnapshot docListBids = bidsRef.orderBy("bid", Query.Direction.DESCENDING).limit(1).get().get().getDocuments().get(0);

        Bid highestBid = docListBids.toObject(Bid.class);
        String username = highestBid.getUsername();
        AucUser highestBidder = userRepository.findByUsername(username);

        highestBidder.setWallet(highestBidder.getWallet() - highestBid.getBid());
        userRepository.save(highestBidder);

        Player item = playerRepository.findBy_id(highestBid.getItemId());
        item.setOwnerId(highestBid.getUsername());
        item.setOwnerName(highestBid.getName());
        playerRepository.save(item);
        auctionReference.update("item", null);
        auctionReference.update("max_bid", null);
        auctionReference.update("max_bid_user", null);
        auctionReference.update("active", "waiting");

        return ResponseEntity.status(200).body(new ResponseObject(200, new ResponseData("Feature under development")));

    }

    public ResponseEntity<ResponseObject> addNewItem(String token, String roomId, String itemId){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);

        Player item = playerRepository.findBy_id(itemId);
        if(item == null){
            ResponseEntity.status(400).body(new ResponseObject(400, new ResponseData("Item not found.")));
        }

        auctionReference.update("item", item);
        auctionReference.update("active", "true");
        return ResponseEntity.status(200).body(new ResponseObject(200, new ResponseData("Item added")));
    }




}
