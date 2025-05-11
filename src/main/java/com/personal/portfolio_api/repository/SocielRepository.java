package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Sociel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocielRepository extends JpaRepository<Sociel, Long> {

    Optional<Sociel> findByFacebook(String facebook);
    Optional<Sociel> findByLinkedInIs(String linkedIn);
    Optional<Sociel> findByInstagram(String instagram);
    Optional<Sociel> findByTwitter(String twitter);

}
