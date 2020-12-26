package com.akcitra.Auctionation.user;


import com.akcitra.Auctionation.models.MongoUser;
import com.akcitra.Auctionation.models.UserDetails;
import com.akcitra.Auctionation.security.PasswordConfig;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserDetailsRepository userDetailsRepository;
    @Autowired private UserRepository userRepository;

    public UserDetails getUserDetails(ObjectId id){
        UserDetails userDetails = userDetailsRepository.findBy_id(id);
        if(userDetails == null) return null;
        else return userDetails;
    }

    public UserDetails setUserDetails(ObjectId id, UserDetails userDetails){
        userDetails.set_id(id);
        userDetailsRepository.save(userDetails);
        return userDetails;
    }

    public MongoUser createUser(MongoUser user){
        MongoUser newUser = userRepository.findByUsername(user.getUsername());
        if(newUser != null) return null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

}
