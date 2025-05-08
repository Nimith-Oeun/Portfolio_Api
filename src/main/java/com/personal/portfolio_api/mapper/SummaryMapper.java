package com.personal.portfolio_api.mapper;

import com.personal.portfolio_api.dto.SummaryDTO;
import com.personal.portfolio_api.model.Summary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SummaryMapper {
    SummaryMapper INSTANCE = Mappers.getMapper(SummaryMapper.class);

    Summary mapToSummary(SummaryDTO summaryDTO);
    SummaryDTO mapToSummaryDTO(Summary summary);
}
