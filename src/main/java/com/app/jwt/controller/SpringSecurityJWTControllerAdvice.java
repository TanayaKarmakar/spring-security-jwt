package com.app.jwt.controller;

import com.app.jwt.commons.ErrorCodes;
import com.app.jwt.exception.AccessDeniedException;
import com.app.jwt.exception.NotFoundException;
import com.app.jwt.models.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class SpringSecurityJWTControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException notFoundException) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(ErrorCodes.NOT_FOUND);
        errorResponseDTO.setDescription(notFoundException.getMessage());
        errorResponseDTO.setTimestamp(ZonedDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorCode(ErrorCodes.ACCESS_DENIED);
        errorResponseDTO.setDescription(accessDeniedException.getMessage());
        errorResponseDTO.setTimestamp(ZonedDateTime.now());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.FORBIDDEN);
    }
}
