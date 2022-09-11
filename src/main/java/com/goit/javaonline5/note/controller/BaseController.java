package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.user.model.UserModel;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String redirectToNoteList(Model model, Principal principal) {
        UserModel byEmail = userRepository.findByEmail(principal.getName());

        model.addAttribute("allNotes",
                userRepository.findById(byEmail.getId()).orElse(new UserModel()).getNotes());

        return "note/note_list";
    }
}
