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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.akcitra.Auctionation.security.SecurityConstants.EXPIRATION_TIME;
import static com.akcitra.Auctionation.security.SecurityConstants.SECRET;

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
        return ResponseEntity.ok(new ResponseObject(new AuthResponseData("User has been created")));
    }

//    public ResponseEntity<ResponseObject> loginUser(AucUser user) throws JSONException {
//        AucUser tempUser = userRepository.findByUsername(user.getUsername());
//        if(tempUser == null) return ResponseEntity.status(401).body(new ResponseObject(404,new AuthResponseData("User doesn't exist")));
//        String token = JWT.create()
//                .withSubject(tempUser.get_id())
//                .withClaim("email", tempUser.getEmail())
//                .withClaim("name", tempUser.getUsername())
//                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .sign(Algorithm.HMAC512(SECRET.getBytes()));
//        System.out.println(token.toString());
//
//        return ResponseEntity.status(200).body(new ResponseObject(401, new AuthResponseData("User Logged In", token.toString())));
//    }
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
