package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProjectRespone {

    private String portfolioDescription;

    private String projectTitle;

    private String projectDescription;

    private long projectPhotoId;




}
