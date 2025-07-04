package com.personal.portfolio_api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.portfolio_api.dto.LoginRequest;
import com.personal.portfolio_api.dto.LoginRespone;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.model.UserProfile;
import com.personal.portfolio_api.repository.ProfileRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ProfileRepository profileRepository;
    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {

            LoginRequest loginRequest = objectMapper.readValue(
                    request.getInputStream(),
                    LoginRequest.class
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse login request: " + e.getMessage());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserProfile userProfile = profileRepository.findByEmail(authResult.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LoginRespone loginRespone = LoginRespone.builder()
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .username(userProfile.getFirstName() + userProfile.getLastName())
                .email(userProfile.getEmail())
                .phoneNumber(userProfile.getPhoneNumber())
                .dateOfBirth(userProfile.getDateOfBirth())
                .degree(userProfile.getDegree())
                .website(userProfile.getWebsite())
                .city(userProfile.getCity())
                .freelance(userProfile.isFreelance())
                .subject(userProfile.getSubject())
                .profileDescription(userProfile.getProfileDescription())
                .subjectDescription(userProfile.getSubjectDescription())
                .build();

        log.info("User {} logged in successfully", userProfile.getUsername());

        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ApiResponeUtils.successRespone(loginRespone)
                )
        );
    }
}
