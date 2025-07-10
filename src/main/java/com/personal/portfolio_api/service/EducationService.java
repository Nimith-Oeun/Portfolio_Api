package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.EducationDTO;

public interface EducationService {
    void updateEducation(Long id, EducationDTO education);
}
