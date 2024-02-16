package com.app.jwt.services.security;

import com.app.jwt.models.entity.User;
import com.app.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        return userOptional.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username +
                        " doesn't exist in the system"));
    }
}
