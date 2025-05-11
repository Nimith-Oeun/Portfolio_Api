package com.personal.portfolio_api.service.impl;



import com.personal.portfolio_api.dto.ResumeDTO;
import com.personal.portfolio_api.dto.ResumeResponeDTO;
import com.personal.portfolio_api.exception.BadRequestException;
import com.personal.portfolio_api.exception.InternalServerError;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.mapper.ResumeMapper;
import com.personal.portfolio_api.model.Education;
import com.personal.portfolio_api.model.Experienc;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.repository.EducationRepository;
import com.personal.portfolio_api.repository.ExperiencRepository;
import com.personal.portfolio_api.repository.ResumeRepository;
import com.personal.portfolio_api.repository.SummaryRepository;
import com.personal.portfolio_api.service.ResumeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;
    private final EducationRepository educationRepository;
    private final SummaryRepository summaryRepository;
    private final ExperiencRepository experiencRepository;

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
                List<Education> education = resumeMapper.mapToEducation(resumeDTO.getEducationReequest());
                List<Education> educationList = education.stream()
                        .peek(edu -> edu.setResumeId(resume))
                        .toList();
                educationRepository.saveAll(educationList);
            }

//            Todo: Save experience;
            if (resumeDTO.getExperienceRequest() != null) {
                List<Experienc> experienc = resumeMapper.mapToExperience(resumeDTO.getExperienceRequest());
                List<Experienc> experiencList = experienc.stream()
                        .peek(ex -> ex.setResumeId(resume))
                        .toList();
                experiencRepository.saveAll(experiencList);
            }

//            Todo: Save summary
            if (resumeDTO.getSummaryRequest() != null) {
                Summary summary = resumeMapper.mapToSummary(resumeDTO.getSummaryRequest());
                summary.setResumeId(resume);
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
    public ResumeResponeDTO getAllResumes() {
        List<Resume> resumeList = resumeRepository.findAll();
        List<Summary> summaryList = summaryRepository.findAll();
        List<Education> educationList = educationRepository.findAll();
        List<Experienc> experiencList = experiencRepository.findAll();

       return ResumeResponeDTO.builder()
                .resumeDescription(resumeList.getFirst().getResumeDescription())
                .education(resumeMapper.mapToEducationReespone(educationList))
                .experience(resumeMapper.mapToExperienceReespone(experiencList))
                .summary(resumeMapper.mapToSummaryResponeDTO(summaryList))
                .build();
    }

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
