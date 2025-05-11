package com.personal.portfolio_api.dto;

import lombok.Data;

@Data
public class EducationResponeDTO {

    private Long id;
    private String universityName;
    private String mejor;
    private String startDate;
    private String endDate;
    private String description;
}
