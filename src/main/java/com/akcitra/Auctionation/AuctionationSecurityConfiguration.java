package com.akcitra.Auctionation;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class AuctionationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/players").permitAll()
                .antMatchers("/auction/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER").and().cors().and().csrf().disable();
    }
}
