package com.personal.portfolio_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterRequest {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;


    private String username;

    private String email;

    private String password;

    private String confirmPassword;
}
