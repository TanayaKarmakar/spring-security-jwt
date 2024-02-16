package com.app.jwt.services.impl;

import com.app.jwt.commons.AppConstants;
import com.app.jwt.exception.AccessDeniedException;
import com.app.jwt.exception.NotFoundException;
import com.app.jwt.models.entity.User;
import com.app.jwt.repository.UserRepository;
import com.app.jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {
        user.setRoles(AppConstants.DEFAULT_ROLE);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User assignRoleToUser(Long userId, String roles, Principal principal) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("User with ID " + userId + " doesn't exist in the system");
        }
        List<String> loggedInUserRoles = extractRolesOfLoggedInUser(principal);
        User user = userOptional.get();
        StringBuilder sb = new StringBuilder();
        if(loggedInUserRoles.contains(roles)) {
            sb.append(user.getRoles())
                    .append(",")
                    .append(roles);
            user.setRoles(sb.toString());
            return userRepository.save(user);

        }
        throw new AccessDeniedException("User " + principal.getName() + " doesn't have enough " +
                "permission to give the roles [" + roles + "] to user " + userOptional.get().getUserName());
    }

    private List<String> extractRolesOfLoggedInUser(Principal principal) {
        Optional<User> userOptional = userRepository.findByUserName(principal.getName());
        if(userOptional.isEmpty()) {
            throw new NotFoundException("User with name " + principal.getName() +
                    " doesn't exist in the system");
        }
        String roles = userOptional.get().getRoles();
        List<String> userRoles = Arrays.stream(roles.split(","))
                .toList();
        if(userRoles.contains(AppConstants.ROLE_ADMIN))
            return Arrays.stream(AppConstants.ADMIN_ROLE).toList();
        if(userRoles.contains(AppConstants.ROLE_MANAGER))
            return Arrays.stream(AppConstants.MANAGER_ROLE).toList();
        return new ArrayList<>();
    }
}
