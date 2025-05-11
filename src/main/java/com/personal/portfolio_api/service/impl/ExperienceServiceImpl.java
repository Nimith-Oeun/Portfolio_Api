package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.ExperienceDTO;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.repository.ExperiencRepository;
import com.personal.portfolio_api.service.EducationService;
import com.personal.portfolio_api.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperiencRepository experiencRepository;


    @Override
    public Experienc updateExperienc(Long id, ExperienceDTO experienceDTO) {
        Experienc experienceId = experiencRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience " + id + " not found"));

        experienceId.setCompanyName(experienceDTO.getCompanyName());
        experienceId.setJobTitle(experienceDTO.getJobTitle());
        experienceId.setStartDate(experienceDTO.getStartDate());
        experienceId.setEndDate(experienceDTO.getEndDate());
        experienceId.setDescription(experienceDTO.getDescription());

        return experiencRepository.save(experienceId);
    }
}
