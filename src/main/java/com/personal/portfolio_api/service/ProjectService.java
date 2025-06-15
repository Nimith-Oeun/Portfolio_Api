package com.personal.portfolio_api.service;

import com.personal.portfolio_api.dto.ProjectRequest;
import com.personal.portfolio_api.model.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(ProjectRequest projectRequest);
    Project updateProject(Long id, ProjectRequest projectRequest);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    void deleteProject(Long id);
}
