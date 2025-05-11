package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDTO {
    @JsonProperty("resume_description")
    private String resumeDescription;

    @JsonProperty("summary_info")
    private SummaryDTO summaryRequest;
    @JsonProperty("experience_info")
    private List<ExperienceDTO> experienceRequest;
    @JsonProperty("education_info")
    private List<EducationDTO> educationReequest;
}
