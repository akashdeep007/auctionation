package com.akcitra.Auctionation.user;


import com.akcitra.Auctionation.models.AucUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;

    public AucUser getUserDetails(String id){
        AucUser userDetails = userRepository.findBy_id(id);
        if(userDetails == null) return null;
        else return userDetails;
    }

    public AucUser setUserDetails(String id, AucUser aucUser){
        userRepository.save(aucUser);
        return aucUser;
    }
}
