package com.app.jwt.models.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String userName;
    private String password;
}
