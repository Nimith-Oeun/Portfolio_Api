package com.personal.portfolio_api.security;

import com.personal.portfolio_api.model.UserProfile;
import com.personal.portfolio_api.repository.ProfileRepository;
import com.personal.portfolio_api.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCurrentUserService {

    private final ProfileRepository userProfileRepository;

    public UserProfile getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Current user: {}", authentication);
        String email = authentication.getName(); // or username depending on your setup

        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
