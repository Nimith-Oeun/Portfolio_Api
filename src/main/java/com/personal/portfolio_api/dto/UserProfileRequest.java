package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserProfileRequest {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private String degree;

    private String website;

    private String city;

    private boolean freelance;

    private String subject;

    @JsonProperty("profile_description")
    private String profileDescription;

    @JsonProperty("subject_description")
    private String subjectDescription;

}
