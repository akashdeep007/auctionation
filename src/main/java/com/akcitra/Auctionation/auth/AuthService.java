package com.akcitra.Auctionation.auth;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.ResponseObject;
import com.akcitra.Auctionation.user.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.akcitra.Auctionation.security.SecurityConstants.EXPIRATION_TIME;
import static com.akcitra.Auctionation.security.SecurityConstants.SECRET;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;


    public ResponseObject createUser(AucUser user){
        AucUser newUser = userRepository.findByUsername(user.getUsername());
        if(newUser != null) return new ResponseObject(404,"User already Exists");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseObject("User Successfully Created");
    }

    public ResponseObject loginUser(AucUser user) throws JSONException {
        AucUser tempUser = userRepository.findByUsername(user.getUsername());
        if(tempUser == null) return new ResponseObject(404,"User doesn't exist");
        String token = JWT.create()
                .withSubject(tempUser.get_id().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        JSONObject data = new JSONObject();
        data.put("token", token.toString());
        return new ResponseObject("User Logged In", data);
    }

    public String encodeJwtToken(){
        return "";
    }
}
