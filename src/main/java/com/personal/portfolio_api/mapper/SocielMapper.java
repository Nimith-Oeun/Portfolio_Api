package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.SocielDTO;
import com.personal.portfolio_api.model.Sociel;
import com.personal.portfolio_api.service.UserProfileService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserProfileService.class})
public interface SocielMapper {
    SocielMapper INSTANCE = Mappers.getMapper(SocielMapper.class);

    // Map Social
    @Mapping(target = "userProfile", source = "userId")
    @Mapping(target = "facebook", source = "facebook", qualifiedByName = "identity")
    @Mapping(target = "twitter", source = "twitter", qualifiedByName = "identity")
    @Mapping(target = "instagram", source = "instagram", qualifiedByName = "identity")
    @Mapping(target = "linkedIn", source = "linkedIn", qualifiedByName = "identity")
    Sociel mapToSociel(SocielDTO socielDTO);

    @Mapping(target = "facebook", source = "facebook", qualifiedByName = "identity")
    @Mapping(target = "twitter", source = "twitter", qualifiedByName = "identity")
    @Mapping(target = "instagram", source = "instagram", qualifiedByName = "identity")
    @Mapping(target = "linkedIn", source = "linkedIn", qualifiedByName = "identity")
    @Mapping(target = "userId", source = "userProfile.id")
    SocielDTO mapToSocielDTO(Sociel sociel);

    @Named("identity")
    default String identity(String value) {
        return value;
    }
}
