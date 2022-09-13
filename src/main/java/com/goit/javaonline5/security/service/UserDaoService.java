package com.goit.javaonline5.security.service;


import com.goit.javaonline5.user.model.UserModel;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserDaoService {

    private final UserRepository userRepository;


    public UserModel save(final UserModel entity) {
        return userRepository.save(entity);
    }


    public UserModel findById(final UUID id) {
        return userRepository.findById(id).orElse(new UserModel());
    }

    public UserModel findByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
