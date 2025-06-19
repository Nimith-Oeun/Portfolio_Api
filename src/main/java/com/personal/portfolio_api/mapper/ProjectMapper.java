package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.ProjectRequest;
import com.personal.portfolio_api.dto.ProjectRespone;
import com.personal.portfolio_api.model.Project;
import com.personal.portfolio_api.model.ProjectPhoto;
import com.personal.portfolio_api.service.ProjectPhotoService;
import com.personal.portfolio_api.service.UserProfileService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {ProjectPhotoService.class , UserProfileService.class})
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "projectPhoto", source = "projectPhotoId")
    @Mapping(target = "userProfile", source = "userId")
    @Mapping(target = "portfolioDescription", source = "portfolioDescription", qualifiedByName = "indentity")
    @Mapping(target = "projectTitle", source = "projectTitle", qualifiedByName = "indentity")
    @Mapping(target = "projectDescription", source = "projectDescription", qualifiedByName = "indentity")
    Project mapToProject(ProjectRequest projectRequest);

    @Mapping(target = "projectPhotoId", source = "projectPhoto.id")
    @Mapping(target = "userId", source = "userProfile.id")
    @Mapping(target = "portfolioDescription", source = "portfolioDescription", qualifiedByName = "indentity")
    @Mapping(target = "projectTitle", source = "projectTitle", qualifiedByName = "indentity")
    @Mapping(target = "projectDescription", source = "projectDescription", qualifiedByName = "indentity")
    ProjectRespone mapToProjectResponse(Project project);

    @Named( "indentity")
    default String indentity(String value) {
        return value;
    }
}
