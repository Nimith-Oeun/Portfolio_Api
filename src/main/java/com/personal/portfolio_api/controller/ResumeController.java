package com.personal.portfolio_api.controller;


import com.personal.portfolio_api.dto.*;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.mapper.ResumeMapper;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.model.Summary;
import com.personal.portfolio_api.service.EducationService;
import com.personal.portfolio_api.service.ExperienceService;
import com.personal.portfolio_api.service.ResumeService;
import com.personal.portfolio_api.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolio/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final SummaryService summaryService;
    private final EducationService educationService;
    private final ExperienceService experienceService;


    @PostMapping("/create")
    public ResponseEntity<?> createResume(@RequestBody ResumeDTO resumeDTO) {
        resumeService.createResume(resumeDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Create Resume Success"));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllResumes() {
        List<ResumeResponeDTO> resumes = resumeService.getAllResumes();
        return ResponseEntity.ok(ApiResponeUtils.successRespone(resumes));
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<?> updateResumeDescription(@PathVariable Long id,
                                                     @RequestBody ResumeDTO resumeDTO) {
        resumeService.updateResume(id, resumeDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Update Resume Success"));
    }

    @PutMapping("/{id}/experience")
    public ResponseEntity<?> updateExperience(@PathVariable Long id,
                                              @RequestBody ExperienceDTO experienceDTO) {
        experienceService.updateExperienc(id, experienceDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Update Summary Success"));
    }

    @PutMapping("/{id}/education")
    public ResponseEntity<?> updateEducation(@PathVariable Long id,
                                             @RequestBody EducationDTO educationDTO) {
        educationService.updateEducation(id, educationDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Update Education Success"));
    }

    @PutMapping("/{id}/summary")
    public ResponseEntity<?> updateSummary(@PathVariable Long id,
                                           @RequestBody SummaryDTO summaryDTO) {
        summaryService.updateSummary(id, summaryDTO);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(ApiResponeUtils.successRespone(" Update Summary Success")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable Long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.ok(ApiResponeUtils.successRespone("Resume deleted successfully"));
    }
}
