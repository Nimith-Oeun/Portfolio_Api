package com.personal.portfolio_api.controller;

import com.personal.portfolio_api.dto.ProjectRequest;
import com.personal.portfolio_api.exception.ApiResponeUtils;
import com.personal.portfolio_api.exception.MaxUploadSizeExceededException;
import com.personal.portfolio_api.mapper.ProjectMapper;
import com.personal.portfolio_api.model.Project;
import com.personal.portfolio_api.service.ProfilePhotoService;
import com.personal.portfolio_api.service.ProjectPhotoService;
import com.personal.portfolio_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/portfolio/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;
    private final ProjectPhotoService projectPhotoService;

    @PostMapping("/create")
    public ResponseEntity<?>createProject(@RequestBody ProjectRequest projectRequest) {
        Project project = projectService.createProject(projectRequest);
        return ResponseEntity.ok(ApiResponeUtils.successRespone(projectMapper.mapToProjectResponse(project)));
    }

    @PostMapping(path = "/upload-photo" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProjectPhoto(@RequestParam("file")MultipartFile file) {
        try {
            //handle file size
            if (file.getSize() > 5 * 1024 * 1024) { // 5 MB limit
                return ResponseEntity.badRequest().body(new MaxUploadSizeExceededException("File too large! Maximum allowed size is 5MB."));
            }

            projectPhotoService.uploadProjectPhoto(file);
            return ResponseEntity.ok(ApiResponeUtils.successRespone("Project photo uploaded successfully"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "message", e.getMessage()));
        }
    }


}
