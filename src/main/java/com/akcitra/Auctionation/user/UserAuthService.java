package com.akcitra.Auctionation.user;

import com.akcitra.Auctionation.models.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;




@Component
public class UserAuthService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository user_repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MongoUser user = user_repository.findByUsername(s);

        if(user == null) throw new UsernameNotFoundException("User not found.");
        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("USER"));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
