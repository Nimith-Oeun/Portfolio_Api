package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findAllByUserProfile(UserProfile userProfile);
}
