package com.akcitra.Auctionation.auth;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.AuthenticationRequest;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> createUser(@RequestBody AucUser aucUser){
        System.out.println(aucUser.toString());
        return authService.createUser(aucUser);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return authService.loginUser(authenticationRequest);
    }
}

