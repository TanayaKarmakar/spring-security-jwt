package com.app.jwt.controller;

import com.app.jwt.models.dto.UserResponseDTO;
import com.app.jwt.models.entity.User;
import com.app.jwt.models.mappers.UserEntityDTOMapper;
import com.app.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(UserEntityDTOMapper.toResponseDTO(createdUser),
                HttpStatus.CREATED);
    }

    @GetMapping("/assign/{userId}/{roles}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<UserResponseDTO> assignRoleToUser(@PathVariable Long userId,
                                                            @PathVariable String roles, Principal principal) {
        User updatedUser = userService.assignRoleToUser(userId, roles, principal);
        return new ResponseEntity<>(UserEntityDTOMapper.toResponseDTO(updatedUser),
                HttpStatus.OK);
    }
}
