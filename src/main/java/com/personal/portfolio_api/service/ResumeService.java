package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.model.Resume;

import java.util.List;

public interface ResumeService {

    Resume createResume(ResumeDTO resumeDTO);
    Resume updateResume(Long id, ResumeDTO resumeDTO);
    Resume getResumeById(Long id);
    List<Resume> getAllResumes();
    void deleteResume(Long id);
}
