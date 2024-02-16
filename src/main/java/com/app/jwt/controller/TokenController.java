package com.app.jwt.controller;

import com.app.jwt.models.dto.AuthRequestDTO;
import com.app.jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public String authenticateAndGenerateToken(@RequestBody AuthRequestDTO authRequestDTO) {
        return authService.authenticateAndGenerateToken(authRequestDTO);
    }
}
