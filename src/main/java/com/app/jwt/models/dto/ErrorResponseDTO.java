package com.app.jwt.models.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ErrorResponseDTO {
    private String errorCode;
    private String description;
    private ZonedDateTime timestamp;
}
