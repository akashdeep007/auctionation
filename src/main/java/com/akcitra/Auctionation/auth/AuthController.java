package com.akcitra.Auctionation.auth;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.ResponseObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/signup")
    public ResponseObject createUser(@RequestBody AucUser aucUser){
        System.out.println(aucUser.toString());
        return authService.createUser(aucUser);
    }

    @PostMapping("/login")
    public ResponseObject loginUser(@RequestBody AucUser aucUser) throws JSONException {
        return authService.loginUser(aucUser);
    }
}

