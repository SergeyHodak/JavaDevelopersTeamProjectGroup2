//package com.goit.javaonline5.user.service.abstraction;
//
//
//import com.goit.javaonline5.user.model.UserModel;
//import com.goit.javaonline5.user.model.UserRegistrationDto;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public interface UserService extends UserDetailsService {
//    UserModel save(UserRegistrationDto registrationDto);
//
//    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
//}