package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.responses.ResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.models.responses.UserData;
import com.akcitra.Auctionation.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private JwtUtils jwtUtils;

    @GetMapping
    public ResponseObject getUserDetails(@RequestHeader("Authorization") String token){
        String username = jwtUtils.extractUsername(token.substring(7));
        if(username == null) return new ResponseObject(404, new ResponseData("Token not Valid"));
        AucUser aucUser = userRepository.findByUsername(username);
        System.out.println(aucUser.getUsername());
        if(aucUser == null) return new ResponseObject(404, new ResponseData("User not found"));
        return new ResponseObject(69, new UserData(aucUser.getName(), aucUser.getUsername(),aucUser.getUsername(), "User Found!"));
    }

    @PutMapping
    public AucUser setUserDetails(@RequestHeader("Authorization") String token, @RequestBody AucUser aucUser){
        userRepository.save(aucUser);
        return aucUser;
    }


}