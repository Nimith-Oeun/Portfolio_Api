package com.personal.portfolio_api.service.impl;



import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.dto.ResumeResponeDTO;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.mapper.ObjectResumeMapper;
import com.personal.portfolio_api.mapper.ResumeMapper;
import com.personal.portfolio_api.model.*;
import com.personal.portfolio_api.repository.*;
import com.personal.portfolio_api.security.GetCurrentUserService;
import com.personal.portfolio_api.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ObjectResumeMapper objectResumeMapper;
    private final ResumeMapper resumeMapper;
    private final EducationRepository educationRepository;
    private final SummaryRepository summaryRepository;
    private final ExperiencRepository experiencRepository;
    private final GetCurrentUserService getCurrentUserService;

    @Transactional
    @Override
    public Resume createResume(ResumeDTO resumeDTO) {

        if(resumeDTO.getSummaryRequest() == null ||
                resumeDTO.getExperienceRequest() == null ||
                resumeDTO.getEducationReequest() == null
        ){
            throw new BadRequestException(" Resume must have summary, experience and education");
        }

        if (resumeDTO.getSummaryRequest().getEmail() != null){

            if (!resumeDTO.getSummaryRequest().getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                throw new InternalServerError("Email " + resumeDTO.getSummaryRequest().getEmail() + " is not Email format");
            }
        }

        if (resumeDTO.getSummaryRequest().getPhoneNumber() != null){
            if (!resumeDTO.getSummaryRequest().getPhoneNumber().matches("^[0-9]{9,10}$")) {
                throw new InternalServerError("Phone number " + resumeDTO.getSummaryRequest().getPhoneNumber() + " is not Phone number format");
            }
        }

        if (Objects.nonNull(summaryRepository.findByEmail(
                resumeDTO.getSummaryRequest().getEmail())
        )){
            throw new BadRequestException("Email is already exist");
        }

        if (Objects.nonNull(summaryRepository.findByPhoneNumber(
                resumeDTO.getSummaryRequest().getPhoneNumber())
        )){
            throw new BadRequestException("Phone number is already exist");
        }

        Resume resume = resumeMapper.mapToResume(resumeDTO);
        Resume saveResume = resumeRepository.save(resume);

        if (resume.getId() != null) {

//            Todo: Save education
            if (resumeDTO.getEducationReequest() != null) {
                List<Education> education = objectResumeMapper.mapToEducation(resumeDTO.getEducationReequest());
                List<Education> educationList = education.stream()
                        .peek(edu -> edu.setResume(resume))
                        .toList();
                educationRepository.saveAll(educationList);
            }

//            Todo: Save experience;
            if (resumeDTO.getExperienceRequest() != null) {
                List<Experienc> experienc = objectResumeMapper.mapToExperience(resumeDTO.getExperienceRequest());
                List<Experienc> experiencList = experienc.stream()
                        .peek(ex -> ex.setResume(resume))
                        .toList();
                experiencRepository.saveAll(experiencList);
            }

//            Todo: Save summary
            if (resumeDTO.getSummaryRequest() != null) {
                Summary summary = objectResumeMapper.mapToSummary(resumeDTO.getSummaryRequest());
                summary.setResume(resume);
                summaryRepository.save(summary);
            }
        }

        return saveResume;
    }

    @Override
    public Resume updateResume(Long id, ResumeDTO resumeDTO) {

        Resume resume = getResumeById(id);

        // Update Resume Description
        resume.setResumeDescription(resumeDTO.getResumeDescription());

        return resumeRepository.save(resume);
    }

    @Override
    public Resume getResumeById(Long id) {
        return  resumeRepository.findById(id)
                .orElseThrow(() -> new ResoureNoteFoundException("Resume "+ id +" not found"));
    }

    @Override
    public List<ResumeResponeDTO> getAllResumes() {

        // Get current user
        UserProfile currentUser = getCurrentUserService.getCurrentUser();

        // Check if user profile exists
        List<Resume> resumeId = resumeRepository.findAllByUserProfile(currentUser);
        if (resumeId.isEmpty()) {
            throw new ResoureNoteFoundException("No resumes created yet");
        }

        return resumeId.stream()
                .map(resume -> {
                    List<Summary> summaryList = summaryRepository.findAllByResume(resume);
                    List<Education> educationList = educationRepository.findAllByResume(resume);
                    List<Experienc> experiencList = experiencRepository.findAllByResume(resume);

                    return ResumeResponeDTO.builder()
                            .resumeDescription(resume.getResumeDescription())
                            .summary(objectResumeMapper.mapToSummaryResponeDTO(summaryList))
                            .education(objectResumeMapper.mapToEducationReespone(educationList))
                            .experience(objectResumeMapper.mapToExperienceReespone(experiencList))
                            .build();
                }).toList();
    }

    @Transactional
    @Override
    public void deleteResume(Long id) {

        Resume resumeId = getResumeById(id);

        // Delete Summary
        summaryRepository.deleteByResumeId(resumeId);

        // Delete Education
        educationRepository.deleteByResumeId(resumeId);

        // Delete Experience
        experiencRepository.deleteByResumeId(resumeId);

        // Delete Resume
        resumeRepository.deleteById(id);
    }

}
