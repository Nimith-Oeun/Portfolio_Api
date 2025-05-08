package com.personal.portfolio_api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorResponeDTO> handleInternalServerError(InternalServerError e){
        log.error("Internal server error: {}", e.getMessage());
        ErrorResponeDTO errorResponse = ErrorResponeDTO.builder()
                .errorCode("500")
                .statusCode("500")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .responeData(new EmptyRespone())
                .build();
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(ResoureNoteFoundException.class)
    public ResponseEntity<ErrorResponeDTO> handleResourceNotFoundException(ResoureNoteFoundException e){
        log.error("Resource not found: {}", e.getMessage());
        ErrorResponeDTO errorResponse = ErrorResponeDTO.builder()
                .errorCode("404")
                .statusCode("404")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .responeData(new EmptyRespone())
                .build();
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponeDTO> handleBadRequestException(BadRequestException e){
        log.error("Bad request: {}", e.getMessage());
        ErrorResponeDTO errorResponse = ErrorResponeDTO.builder()
                .errorCode("400")
                .statusCode("400")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .responeData(new EmptyRespone())
                .build();
        return ResponseEntity.status(400).body(errorResponse);
    }

}