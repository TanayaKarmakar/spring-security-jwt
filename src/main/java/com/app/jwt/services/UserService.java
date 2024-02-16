package com.app.jwt.services;


import com.app.jwt.models.entity.User;

import java.security.Principal;

public interface UserService {
    User createUser(User user);

    User assignRoleToUser(Long userId, String roles, Principal principal);
}
