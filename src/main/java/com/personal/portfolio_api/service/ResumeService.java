package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.EducationDTO;
import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.dto.ResumeResponeDTO;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;

import java.util.List;

public interface ResumeService {

    Resume createResume(ResumeDTO resumeDTO);
    Resume updateResume(Long id, ResumeDTO resumeDTO);
    Resume getResumeById(Long id);
    ResumeResponeDTO getAllResumes();
    void deleteResume(Long id);


}
