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

    @JsonProperty("project_photo_id")
    private long projectPhotoId;

    @JsonProperty("user_id")
    private Long userId;

}
