package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
