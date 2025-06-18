package com.personal.portfolio_api.controller;

import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.model.UserProfile;
import com.personal.portfolio_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/portfolio/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserProfileService userProfileService;

    @PutMapping("/update_profile/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id,
                                           @RequestBody UserProfileRequest userProfileRequest) {
        userProfileService.updateUserProfile(id, userProfileRequest);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Update Profile Success"));
    }

    @GetMapping("/get_profile/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        UserProfile profileById = userProfileService.getUserProfileById(id);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(profileById));
    }

    @GetMapping("/getAll_profile")
    public ResponseEntity<?> getAllProfiles() {
        return ResponseEntity.ok(ApiResponeUtils.successRespone(
                userProfileService.getAllUserProfiles()));
    }
}
