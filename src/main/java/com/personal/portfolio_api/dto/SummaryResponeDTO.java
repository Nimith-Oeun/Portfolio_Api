package com.personal.portfolio_api.dto;

import lombok.Data;

@Data
public class SummaryResponeDTO {

    private Long id;
    private String name;
    private String nationality;
    private String email;
    private String phoneNumber;
    private String address;
    private String Status;
}
