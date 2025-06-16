package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.UserProfileRequest;
import com.personal.portfolio_api.dto.UserProfileRespone;
import com.personal.portfolio_api.model.UserProfile;
import com.personal.portfolio_api.service.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {


    UserProfile mapToUserProfile(UserProfileRequest userProfileRequest);

    UserProfileRespone mapToUserProfileResponse(UserProfile userProfile);
}
