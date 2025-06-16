package com.personal.portfolio_api.controller;

import com.personal.portfolio_api.dto.RegisterRequest;
import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/portfolio/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final UserProfileService userProfileService;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        userProfileService.createUserProfile(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token) {
        String confirmToken = userProfileService.confirmToken(token);
        return ResponseEntity.ok(confirmToken);
    }
}
