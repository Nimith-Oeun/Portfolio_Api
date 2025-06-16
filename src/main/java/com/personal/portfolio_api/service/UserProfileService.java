package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.RegisterRequest;
import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.model.UserProfile;

import java.util.Optional;

public interface UserProfileService {
    Optional<UserProfile> findByEmail(String email);
    UserProfile updateUserProfile(Long id, UserProfileRequest userProfileRequest);
    UserProfile createUserProfile(RegisterRequest registerRequest);
    String confirmToken(String token);
}
