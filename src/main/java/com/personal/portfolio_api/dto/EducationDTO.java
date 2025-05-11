package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.portfolio_api.model.Resume;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EducationDTO {

    @JsonProperty("university_name")
    private String universityName;
    private String mejor;
    @JsonProperty( "start_date")
    private String startDate;
    @JsonProperty( "end_date")
    private String endDate;
    private String description;
}
