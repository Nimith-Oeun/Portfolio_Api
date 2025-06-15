package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.ProjectRequest;
import com.personal.portfolio_api.dto.ProjectRespone;
import com.personal.portfolio_api.model.Project;
import com.personal.portfolio_api.model.ProjectPhoto;
import com.personal.portfolio_api.service.ProjectPhotoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {ProjectPhotoService.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "projectPhoto", source = "projectPhotoId")
    Project mapToProject(ProjectRequest projectRequest);

    @Mapping(target = "projectPhotoId", source = "projectPhoto.id")
    ProjectRespone mapToProjectResponse(Project project);
}
