package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    Summary findByEmail(String email);

    Summary findByPhoneNumber(String phoneNumber);

    List<Summary> findAllByResume(Resume resume);

    void deleteByResumeId(Resume resumeId);

}
