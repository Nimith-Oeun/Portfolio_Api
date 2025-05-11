package com.personal.portfolio_api.dto;


import lombok.Data;

@Data
public class ExperienceResponeDTO {

    private Long id;
    private String companyName;
    private String jobTitle;
    private String startDate;
    private String endDate;
    private String description;

}
