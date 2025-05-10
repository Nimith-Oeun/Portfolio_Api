package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.mapper.ResumeMapper;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.repository.EducationRepository;
import com.personal.portfolio_api.repository.ExperiencRepository;
import com.personal.portfolio_api.repository.ResumeRepository;
import com.personal.portfolio_api.repository.SummaryRepository;
import com.personal.portfolio_api.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final EducationRepository educationRepository;
    private final SummaryRepository summaryRepository;
    private final ExperiencRepository experiencRepository;

    @Transactional
    @Override
    public Resume createResume(ResumeDTO resumeDTO) {

        Resume resume = resumeMapper.mapToResume(resumeDTO);
        Resume saveResume = resumeRepository.save(resume);

        if (resume.getId() != null) {

            // Save education
            if (resumeDTO.getEducationReequest() != null) {
                List<Education> education = resumeMapper.mapToEducation(resumeDTO.getEducationReequest());
                List<Education> educationList = education.stream()
                        .peek(edu -> edu.setResumeId(resume))
                        .toList();
                educationRepository.saveAll(educationList);
            }

            // Save experience
            if (resumeDTO.getExperienceRequest() != null) {
                List<Experienc> experienc = resumeMapper.mapToExperience(resumeDTO.getExperienceRequest());
                List<Experienc> experiencList = experienc.stream()
                        .peek(ex -> ex.setResumeId(resume))
                        .toList();
                experiencRepository.saveAll(experiencList);
            }

            // Save summary
            if (resumeDTO.getSummaryRequest() != null) {
                Summary summary = resumeMapper.mapToSummary(resumeDTO.getSummaryRequest());
                summary.setResumeId(resume);
                summaryRepository.save(summary);
            }
        }


        return saveResume;
    }

    @Override
    public Resume updateResume(Long id, ResumeDTO resumeDTO) {
        return null;
    }

    @Override
    public Resume getResumeById(Long id) {
        return  resumeRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Resume "+ id +" not found"));
    }

    @Override
    public List<Resume> getAllResumes() {
        return null;
    }

    @Override
    public void deleteResume(Long id) {

    }
}
