package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.*;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ObjectResumeMapper {

    //    Map Summary
    Summary mapToSummary(SummaryDTO summaryDTO);
    List<SummaryResponeDTO> mapToSummaryResponeDTO(List<Summary> summary);

//    Map Education

    List<Education> mapToEducation(List<EducationDTO> educationDTO);
    List<EducationResponeDTO> mapToEducationReespone(List<Education> education);

//    Map Experience

    List<Experienc> mapToExperience(List<ExperienceDTO> experienceDTO);
    List<ExperienceResponeDTO> mapToExperienceReespone(List<Experienc> experience);

    // Custom mapping for Long to Resume
    default Resume map(Long resumeId) {
        if (resumeId == null) {
            return null;
        }
        Resume resume = new Resume();
        resume.setId(resumeId); // Assuming Resume has a setId method
        return resume;
    }
}
