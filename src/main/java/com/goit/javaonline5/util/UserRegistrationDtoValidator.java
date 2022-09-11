package com.goit.javaonline5.util;

import com.goit.javaonline5.user.model.UserRegistrationDto;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class UserRegistrationDtoValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ofNullable(userRepository
                .findByEmail(((UserRegistrationDto) target).getEmail()))
                .ifPresent(x -> errors
                        .rejectValue("email", "", "User with this email already exists"));

    }
}
