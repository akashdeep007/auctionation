package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.requests.AddItemRequest;
import com.akcitra.Auctionation.models.requests.AuctionCreateRequest;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = {"http://localhost:8081", "http://localhost:8080"})
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired AuctionService auctionService;

    @PostMapping("/room/{roomId}")
    public ResponseEntity<ResponseObject> bid(@RequestHeader("Authorization") String token, @PathVariable String roomId, @RequestBody Bid bid) throws ExecutionException, InterruptedException {
        System.out.println(bid.getUsername());
        return auctionService.addBid(token, roomId, bid);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createRoom(@RequestBody AuctionCreateRequest auctionCreateRequest){
        return auctionService.createAuction(auctionCreateRequest);
    }

    @GetMapping("/room/{room_name}")
    public ResponseEntity<ResponseObject> enterRoom(@RequestHeader("Authorization") String token, @PathVariable String room_name) throws ExecutionException, InterruptedException {
        return auctionService.getRoom(token, room_name);
    }

    @PostMapping("/end")
    public ResponseEntity<ResponseObject> endAuction(@RequestHeader("Authorization") String token, @RequestBody AddItemRequest itemRequest){
        System.out.println("End Auction Endpoint");
        return auctionService.endAuction(token, itemRequest.getRoomId());
    }

    @PutMapping("/room")
    public ResponseEntity<ResponseObject> addItem(@RequestHeader("Authorization") String token, @RequestBody AddItemRequest itemRequest){
        System.out.println("Add Item Endpoint");
        return auctionService.addNewItem(token, itemRequest.getRoomId(), itemRequest.getItemId());
    }

    @PostMapping("/room")
    public ResponseEntity<ResponseObject> endRound(@RequestHeader("Authorization") String token, @RequestBody AddItemRequest itemRequest) throws ExecutionException, InterruptedException {
        System.out.println("End Round Endpoint");
        return auctionService.endRound(token, itemRequest.getRoomId(), itemRequest.getItemId());
    }

    @GetMapping("/subscription")
    public ResponseEntity<ResponseObject> firebaseSubscription(@RequestHeader("Authorization") String token, @RequestBody String roomId){
        return ResponseEntity.status(200).body(new ResponseObject(200, new ResponseData("Firebase Bids collection susbcribed.")));
    }
}
