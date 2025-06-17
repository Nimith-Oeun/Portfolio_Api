package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRespone {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String phoneNumber;

    private String dateOfBirth;

    private String degree;

    private String website;

    private String city;

    private boolean freelance;

    private String subject;

    private String profileDescription;

    private String subjectDescription;
}
