package com.personal.portfolio_api.exception;

import java.time.LocalDateTime;

public class ApiResponeUtils {

    public static ErrorResponeDTO buildErrorResponeDTO(String errorCode, String statusCode,
                                                       String message,Object responeData){
        return ErrorResponeDTO.builder()
                .errorCode(errorCode)
                .statusCode(statusCode)
                .message(message)
                .timestamp(LocalDateTime.now())
                .responeData(responeData)
                .build();
    }

    public static ErrorResponeDTO successRespone(Object object){
        return ApiResponeUtils.buildErrorResponeDTO(
                "200",
                "OK",
                "Success",
                object
        );
    }
}