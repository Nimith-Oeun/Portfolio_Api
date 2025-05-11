package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.SocielDTO;
import com.personal.portfolio_api.model.Sociel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SocielMapper {
    SocielMapper INSTANCE = Mappers.getMapper(SocielMapper.class);

    // Map Social
    Sociel mapToSociel(SocielDTO socielDTO);
    SocielDTO mapToSocielDTO(Sociel sociel);
}
