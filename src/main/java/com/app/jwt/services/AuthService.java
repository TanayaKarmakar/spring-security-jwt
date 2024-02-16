package com.app.jwt.services;

import com.app.jwt.models.dto.AuthRequestDTO;

public interface AuthService {
    String authenticateAndGenerateToken(AuthRequestDTO authRequestDTO);
}
