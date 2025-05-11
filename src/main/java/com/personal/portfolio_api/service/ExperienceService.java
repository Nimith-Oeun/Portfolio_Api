package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.ExperienceDTO;
import com.personal.portfolio_api.model.Experienc;

public interface ExperienceService {

    Experienc updateExperienc(Long id, ExperienceDTO experienceDTO);
}
