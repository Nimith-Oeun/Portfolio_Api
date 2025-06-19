package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.LoginRequest;
import com.personal.portfolio_api.dto.RegisterRequest;
import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.dto.UserProfileRespone;
import com.personal.portfolio_api.model.UserProfile;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfile> findByEmail(String email);
    UserProfile updateUserProfile(Long id, UserProfileRequest userProfileRequest);
    UserProfile getUserProfileById(Long id);
    UserProfile createUserProfile(RegisterRequest registerRequest);
    List<UserProfileRespone> getAllUserProfiles();
    String confirmToken(String token);
}
