package com.goit.javaonline5.user.controller;

import com.goit.javaonline5.user.model.UserModel;

import com.goit.javaonline5.user.service.abstraction.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserModel userModel() {
        return new UserModel();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "user/registration";
    }

    @PostMapping
    public String registerUserAccount(@Valid UserModel userModel, BindingResult bindingResult) throws Exception {
        System.out.println(userModel.toString());
        if (bindingResult.hasErrors()) {
            return "user/registration";
        } else {
            userService.save(userModel);
            return "redirect:/registration?success";
        }


    }
}
