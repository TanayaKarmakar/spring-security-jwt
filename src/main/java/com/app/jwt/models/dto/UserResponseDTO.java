package com.app.jwt.models.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userName;
    private boolean isActive;
    private String roles;
}
