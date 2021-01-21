package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Auction;
import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.requests.AuctionCreateRequest;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.util.JwtUtils;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Service
public class AuctionService {

    @Autowired JwtUtils jwtUtils;


    public ResponseEntity<ResponseObject> addBid(String roomId, Bid bid) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference auctionReference = dbFirestore.collection("auction").document(roomId);
        Auction auction = auctionReference.get().get().toObject(Auction.class);

        if(auction.getEndTime() < System.currentTimeMillis())
            return ResponseEntity.status(500).body(new ResponseObject(500, new ResponseData("Expired")));


        String newBidId = auctionReference.collection("bids").document().getId();
        auctionReference.collection("bids").document().set(bid);

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Bid Added")));
    }

    public ResponseEntity<ResponseObject> createAuction(AuctionCreateRequest auctionCreateRequest){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference auctionReference = dbFirestore.collection("auction");
        Auction newAuction = new Auction(
                auctionCreateRequest.getAuctionTitle(),
                auctionCreateRequest.getStartTime(),
                auctionCreateRequest.getEndTime(),
                auctionCreateRequest.getParticipants()
        );
        auctionReference.document().set(newAuction);
        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("Auction Created")));
    }

    public ResponseEntity<ResponseObject> getRoom(String token, String roomId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String username = jwtUtils.extractUsername(token.substring(7));
        if(username == null) return ResponseEntity.status(404).body(new ResponseObject(404, new ResponseData("User not Found")));

        Auction auction = dbFirestore.collection("auction").document(roomId).get().get().toObject(Auction.class);

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

        return ResponseEntity.status(200).body(new ResponseObject(69, new ResponseData("OK")));

    }


}
