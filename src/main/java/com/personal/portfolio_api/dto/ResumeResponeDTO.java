package com.personal.portfolio_api.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResumeResponeDTO {

    private String resumeDescription;
    private List<SummaryResponeDTO> summary;
    private List<ExperienceResponeDTO> experience;
    private List<EducationResponeDTO> education;
}
