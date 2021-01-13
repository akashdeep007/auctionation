package com.akcitra.Auctionation.auth;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.AuthenticationRequest;
import com.akcitra.Auctionation.models.responses.AuthResponseData;
import com.akcitra.Auctionation.models.responses.ResponseObject;
import com.akcitra.Auctionation.user.UserRepository;
import com.akcitra.Auctionation.util.JwtUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private JwtUtils jwtUtils;

    public ResponseEntity<ResponseObject> createUser(AucUser user){
        AucUser newUser = userRepository.findByUsername(user.getUsername());
        if(newUser != null) return ResponseEntity.status(404).body(new ResponseObject(404,new AuthResponseData("User already Exists")));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = jwtUtils.encodeJwt(new AuthenticationRequest(user.getUsername(), user.getPassword()));
        return ResponseEntity.ok(new ResponseObject(new AuthResponseData("User has been created", token)));
    }

public ResponseEntity<ResponseObject> loginUser(AuthenticationRequest user) throws Exception {
    try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    } catch (BadCredentialsException e){
        throw new Exception("Incorrect Username and Password", e);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
    final String jwtToken = jwtUtils.encodeJwt(user);

    return ResponseEntity.status(200).body(new ResponseObject(200, new AuthResponseData("User Logged In", jwtToken)));
}

}
