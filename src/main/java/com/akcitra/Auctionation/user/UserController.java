package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.MongoUser;
import com.akcitra.Auctionation.models.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserDetailsRepository userDetailsRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    public MongoUser createUser(@RequestBody MongoUser user){
        return userService.createUser(user);
    }

    @GetMapping("/")
    public UserDetails getUserDetails(@PathVariable("id") ObjectId id){
        UserDetails userDetails = userDetailsRepository.findBy_id(id);
        return userDetails;
    }

    @PostMapping("/")
    public UserDetails setUserDetails(@RequestBody UserDetails userDetails){
        userDetailsRepository.save(userDetails);
        return userDetails;
    }


}