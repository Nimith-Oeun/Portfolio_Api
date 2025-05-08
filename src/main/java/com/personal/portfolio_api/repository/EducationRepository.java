package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
