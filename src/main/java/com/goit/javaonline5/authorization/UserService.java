package com.goit.javaonline5.authorization;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserModel save(UserRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}