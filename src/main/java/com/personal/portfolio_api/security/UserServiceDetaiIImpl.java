package com.personal.portfolio_api.security;

import com.personal.portfolio_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDetaiIImpl implements UserDetailsService {

    private final UserProfileService userProfileService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userProfileService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
