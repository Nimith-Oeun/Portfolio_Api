package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.EducationDTO;
import com.personal.portfolio_api.model.Education;

public interface EducationService {
    Education updateEducation(Long id, EducationDTO education);
}
