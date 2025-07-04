package com.personal.portfolio_api.service;

import com.personal.portfolio_api.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken confirmationToken);
    Optional<ConfirmationToken> getToken(String token);
    int setConfirmedAt(String token);
}