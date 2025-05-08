package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    Summary findByEmail(String email);

    Summary findByPhoneNumber(String phoneNumber);
}
