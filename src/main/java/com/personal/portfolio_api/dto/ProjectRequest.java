package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectRequest {

    @JsonProperty("portfolio_description")
    private String portfolioDescription;

    @JsonProperty("project_title")
    private String projectTitle;

    @JsonProperty("project_description")
    private String projectDescription;

    private long projectPhotoId;

}
