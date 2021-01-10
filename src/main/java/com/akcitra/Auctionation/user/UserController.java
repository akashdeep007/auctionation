package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.AucUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;

    @GetMapping("/{id}")
    public AucUser getUserDetails(@PathVariable("id") String id){
        AucUser userDetails = userRepository.findBy_id(id);
        return userDetails;
    }

    @PutMapping("/{id}")
    public AucUser setUserDetails(@PathVariable("id") String username, @RequestBody AucUser aucUser){
        userRepository.save(aucUser);
        return aucUser;
    }


}