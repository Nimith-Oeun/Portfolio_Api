package com.personal.portfolio_api.service.impl;

import com.personal.portfolio_api.dto.ProjectRequest;
import com.personal.portfolio_api.exception.ResoureNoteFoundException;
import com.personal.portfolio_api.mapper.ProjectMapper;
import com.personal.portfolio_api.model.Project;
import com.personal.portfolio_api.model.ProjectPhoto;
import com.personal.portfolio_api.repository.ProjectPhotoRepository;
import com.personal.portfolio_api.repository.ProjectRepository;
import com.personal.portfolio_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectPhotoRepository projectPhotoRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Project createProject(ProjectRequest projectRequest) {

        projectPhotoRepository.findById(Long.valueOf(projectRequest.getProjectPhotoId()))
                .orElseThrow(() -> new ResoureNoteFoundException("Project photo not found"));

        Project toProject = projectMapper.mapToProject(projectRequest);
        return projectRepository.save(toProject);
    }

    @Override
    public Project updateProject(Long id, ProjectRequest projectRequest) {
        return null;
    }

    @Override
    public Project getProjectById(Long id) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return List.of();
    }

    @Override
    public void deleteProject(Long id) {

    }
}
