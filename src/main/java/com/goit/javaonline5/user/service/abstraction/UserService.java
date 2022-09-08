package com.goit.javaonline5.user.service.abstraction;


import com.goit.javaonline5.user.model.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto registrationDto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}