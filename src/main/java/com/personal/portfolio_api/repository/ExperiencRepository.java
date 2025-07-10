package com.personal.portfolio_api.repository;

import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperiencRepository extends JpaRepository<Experienc, Long> {
    void deleteByResumeId(Resume resumeId);
    List<Experienc> findAllByResume(Resume resume);

}
