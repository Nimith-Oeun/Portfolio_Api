package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.*;
import com.personal.portfolio_api.model.Resume;
import com.personal.portfolio_api.service.UserProfileService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {UserProfileService.class })
public interface ResumeMapper {
    ResumeMapper INSTANCE = Mappers.getMapper(ResumeMapper.class);

//    Map Resume

    @Mapping(target = "userProfile", source = "userProfileId")
    @Mapping(target = "resumeDescription", source = "resumeDescription", qualifiedByName = "identity")
    Resume mapToResume(ResumeDTO resumeDTO);

    @Mapping(target = "userProfileId", source = "userProfile.id")
    @Mapping(target = "resumeDescription", source = "resumeDescription", qualifiedByName = "identity")
    ResumeDTO mapToResumeDTO(Resume resume);

    @Named("identity")
    default String identity(String value) {
        return value;
    }


}
