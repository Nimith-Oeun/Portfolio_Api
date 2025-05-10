package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.EducationDTO;
import com.personal.portfolio_api.dto.ExperienceDTO;
import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.service.ResumeService;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

//    Map Resume

    Resume mapToResume(ResumeDTO resumeDTO);
    ResumeDTO mapToResumeDTO(Resume resume);

//    Map Summary
    @Mapping(target = "resumeId", source = "resumeId")
    Summary mapToSummary(SummaryDTO summaryDTO);

//    Map Education

    List<Education> mapToEducation(List<EducationDTO> educationDTO);

//    Map Experience

    List<Experienc> mapToExperience(List<ExperienceDTO> experienceDTO);

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
