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
}
