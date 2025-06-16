package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.model.ConfirmationToken;
import com.personal.portfolio_api.repository.ConfirmationTokenRepository;
import com.personal.portfolio_api.service.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {

        log.info("Saving confirmation token: {}", confirmationToken.getToken());
        confirmationTokenRepository.save(confirmationToken);
        log.info("Confirmation token saved successfully");
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {

        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token,
                LocalDateTime.now ()
        );
    }
}