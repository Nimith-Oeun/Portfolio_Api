package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.Email.EmailPlatform;
import com.personal.portfolio_api.Email.EmailSender;
import com.personal.portfolio_api.dto.LoginRequest;
import com.personal.portfolio_api.dto.RegisterRequest;
import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.enumerat.Role;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.model.ConfirmationToken;
import com.personal.portfolio_api.model.UserProfile;
import com.personal.portfolio_api.repository.ProfileRepository;
import com.personal.portfolio_api.service.ConfirmationTokenService;
import com.personal.portfolio_api.service.UserProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    @Override
    public Optional<UserProfile> findByEmail(String email) {
        UserProfile userProfile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User profile not found for email: " + email));
        log.info("User profile found for email: {}", email);
        return Optional.ofNullable(userProfile);
    }

    @Override
    public UserProfile createUserProfile(RegisterRequest registerRequest) {

        if (profileRepository.existsByEmail(registerRequest.getEmail())) {
            log.error("User profile already exists for email: {}", registerRequest.getEmail());
            throw new RuntimeException("User profile already exists for email: " + registerRequest.getEmail());
        }

        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(registerRequest.getEmail());
        userProfile.setFirstName(registerRequest.getFirstName());
        userProfile.setLastName(registerRequest.getLastName());
        userProfile.setUsername(registerRequest.getUsername());
        userProfile.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userProfile.setRole(Role.ADMIN);

        profileRepository.save(userProfile);

        //TODO: Send comfirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userProfile
        );

        /*
            TODO: Save confirmation token
         */
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        /*
            TODO: send to Email
         */
        String link = "http://localhost:8080/api/v1/portfolio/auth/confirm?token=" + token;
        emailSender.send(
                registerRequest.getEmail(),
                EmailPlatform.buildEmail(registerRequest.getFirstName(),link)
        );

        return userProfile;
    }


    @Override
    public UserProfile updateUserProfile(Long id, UserProfileRequest userProfileRequest) {

        UserProfile userProfile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User profile not found with id: " + id));

        userProfile.setFirstName(userProfileRequest.getFirstName());
        userProfile.setLastName(userProfileRequest.getLastName());
        userProfile.setUsername(userProfileRequest.getUsername());
        userProfile.setPhoneNumber(userProfileRequest.getPhoneNumber());
        userProfile.setDateOfBirth(userProfileRequest.getDateOfBirth());
        userProfile.setDegree(userProfileRequest.getDegree());
        userProfile.setWebsite(userProfileRequest.getWebsite());
        userProfile.setCity(userProfileRequest.getCity());
        userProfile.setFreelance(userProfileRequest.isFreelance());
        userProfile.setSubject(userProfileRequest.getSubject());
        userProfile.setSubjectDescription(userProfileRequest.getSubjectDescription());

        return profileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUserProfileById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("User profile not found with id: " + id));
    }

    @Override
    public String confirmToken(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));


        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiryAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableAppUser(confirmationToken.getUserProfile().getEmail());

        return "confirmed";
    }

    public int enableAppUser(String email) {
        return profileRepository.enableAppUser(email);
    }

}
