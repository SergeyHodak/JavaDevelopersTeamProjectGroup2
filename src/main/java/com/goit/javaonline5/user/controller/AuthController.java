package com.goit.javaonline5.user.controller;

import com.goit.javaonline5.security.service.RegistrationService;
import com.goit.javaonline5.user.model.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userModel") UserRegistrationDto userRegistrationDto) {

        return "user/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userModel") UserRegistrationDto person) {
        registrationService.register(person);

        return "redirect:/user/login";
    }
}
