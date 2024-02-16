package com.app.jwt.services.impl;

import com.app.jwt.exception.InvalidRequestException;
import com.app.jwt.exception.NotFoundException;
import com.app.jwt.models.dto.AuthRequestDTO;
import com.app.jwt.services.AuthService;
import com.app.jwt.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String authenticateAndGenerateToken(AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUserName(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDTO.getUserName());
        }

        throw new InvalidRequestException("Invalid Request");
    }
}
