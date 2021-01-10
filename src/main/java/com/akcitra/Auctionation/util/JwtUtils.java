package com.akcitra.Auctionation.util;

import com.akcitra.Auctionation.models.AucUser;
import com.akcitra.Auctionation.models.AuthenticationRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.akcitra.Auctionation.security.SecurityConstants.EXPIRATION_TIME;
import static com.akcitra.Auctionation.security.SecurityConstants.SECRET;

@Service
public class JwtUtils {
    public String encodeJwt(AuthenticationRequest authenticationRequest){
        String token = JWT.create()
            .withSubject(authenticationRequest.getUsername())
            .withClaim("username", authenticationRequest.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.getBytes()));
        return token;
    }

    public Map<String, Claim> decodeJWT(String jwtToken){
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        return decodedJWT.getClaims();
    }

    public String extractUsername(String jwtToken){
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        return(decodedJWT.getClaim("username").asString());
    }
}
