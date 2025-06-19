package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.CoverPhotoDTO;
import com.personal.portfolio_api.dto.ProfilePhotoDTO;
import com.personal.portfolio_api.dto.SkillDTO;
import com.personal.portfolio_api.model.CoverPhoto;
import com.personal.portfolio_api.model.ProfilePhoto;
import com.personal.portfolio_api.model.Skill;
import com.personal.portfolio_api.service.UserProfileService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring" , uses = {UserProfileService.class})
public interface PhotoMapper {

    @Mapping(target = "userProfileID", source = "userId")
    ProfilePhoto mapToProfilePhoto(ProfilePhotoDTO profilePhotoDTO);

    @Mapping(target = "userProfileID", source = "userId")
    CoverPhoto mapToCoverPhoto(CoverPhotoDTO coverPhotoDTO);

    @Mapping(target = "userProfileID", source = "userId")
    @Mapping(target = "skillTitle", source = "skillTitle", qualifiedByName = "identity")
    Skill mapToSkill(SkillDTO skillDTO);

    @Named( "identity")
    default String identity(String value) {
        return value;
    }
}
