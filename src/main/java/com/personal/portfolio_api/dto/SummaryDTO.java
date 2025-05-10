package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.portfolio_api.enumerat.SummaryStatus;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SummaryDTO {

    private String name;
    private String nationality;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    private String Status;
    @JsonProperty("resume_id")
    private Long resumeId;
}
