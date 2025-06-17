package com.personal.portfolio_api.controller;

import com.personal.portfolio_api.dto.RegisterRequest;
import com.personal.portfolio_api.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/portfolio/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserProfileService userProfileService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        userProfileService.createUserProfile(registerRequest);
        return ResponseEntity.ok("Please Activate your account by clicking the link sent to your email.");
    }

    @GetMapping("confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token) {
        String confirmToken = userProfileService.confirmToken(token);
        return ResponseEntity.ok(confirmToken);
    }

}
