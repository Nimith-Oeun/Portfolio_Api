package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.EducationDTO;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.repository.EducationRepository;
import com.personal.portfolio_api.service.EducationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    @Override
    public void updateEducation(Long id, EducationDTO educationDTO) {
        Education educationId = educationRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Education " + id + " not found"));

        educationId.setUniversityName(educationDTO.getUniversityName());
        educationId.setMejor(educationDTO.getMejor());
        educationId.setStartDate(educationDTO.getStartDate());
        educationId.setEndDate(educationDTO.getEndDate());
        educationId.setDescription(educationDTO.getDescription());

        educationRepository.save(educationId);

    }
}
