package com.akcitra.Auctionation.auction;

import com.akcitra.Auctionation.models.Bid;
import com.akcitra.Auctionation.models.requests.AuctionCreateRequest;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auction")
public class AuctionController {

    @Autowired AuctionService auctionService;

    @PostMapping("/bid")
    public ResponseEntity<ResponseObject> bid(@PathVariable String roomId, @RequestBody Bid bid) throws ExecutionException, InterruptedException {
        return auctionService.addBid(roomId, bid);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createRoom(@RequestBody AuctionCreateRequest auctionCreateRequest){
        return auctionService.createAuction(auctionCreateRequest);
    }

    @PostMapping("/room")
    public ResponseEntity<ResponseObject> enterRoom(@RequestHeader("Authorization") String token, @PathVariable String roomId) throws ExecutionException, InterruptedException {
        return auctionService.getRoom(token, roomId);
    }
}
