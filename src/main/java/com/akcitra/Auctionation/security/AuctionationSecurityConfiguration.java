package com.akcitra.Auctionation.security;

import com.akcitra.Auctionation.user.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AuctionationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthService userAuthService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuctionationSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/players").authenticated()
                .antMatchers("/auction/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER")
                .and().cors()
                .and().sessionManagement().disable();
    }

    @Override
    protected org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        UserDetails fooUser = User.builder()
                .username("foo")
                .password(passwordEncoder.encode("foo"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(fooUser);
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userAuthService);
    }
}
