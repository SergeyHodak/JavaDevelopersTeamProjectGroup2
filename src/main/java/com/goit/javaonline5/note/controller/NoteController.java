package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.enums.AccessType;
import com.goit.javaonline5.note.model.NoteModel;
import com.goit.javaonline5.user.model.UserModel;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteDaoService noteDaoService;

    private final UserRepository userRepository;

    @GetMapping("/list")
    public String allNotesPage(Model model, Principal principal) {
        UserModel byEmail = userRepository.findByEmail(principal.getName());

        model.addAttribute("allNotes",
                userRepository.findById(byEmail.getId()).orElse(new UserModel()).getNotes());

        return "note/note_list";
    }

    @ModelAttribute("access_types")
    public List<AccessType> getAllAccessTypes() {
        return new ArrayList<>(AccessType.getAllValues());
    }

    @GetMapping("/create")
    public String newNotePage(@ModelAttribute("note") NoteModel noteModel,
                              @ModelAttribute("access_types") List<AccessType> accessTypes) {
        return "note/new";
    }

    @PostMapping("/create")
    public String addNewNote(@ModelAttribute("note") @Valid NoteModel noteModel,
                             final BindingResult bindingResult,
                             @RequestParam("access_type") String accessType,
                             Principal principal
    ) {
        if (bindingResult.hasErrors()) return "note/new";

        noteModel.setUserId(userRepository.findByEmail(principal.getName()).getId());
        noteModel.setAccessType(AccessType.valueOf(accessType));
        noteDaoService.save(noteModel);

        return "redirect:/note/list";
    }

    @GetMapping("/share/{id}")
    public String certainNoteIdPage(@PathVariable("id") String stringId, Model model, Principal principal) {
        UUID id;
        try {
            id = UUID.fromString(stringId);
        } catch (IllegalArgumentException e) {
            return "note/note_not_found";
        }

        NoteModel note = noteDaoService.findById(id);

        if (note.getId() == null) {
            return "note/note_not_found";
        }

        if (note.getAccessType() == AccessType.PUBLIC) {
            model.addAttribute("general", note);
            return "note/note_share";
        }

        if (note.getAccessType() == AccessType.PRIVATE) {
            UUID userId = null;
            if (principal != null) {
                userId = userRepository.findByEmail(principal.getName()).getId();
            }
            if (note.getUserId().equals(userId)) {
                model.addAttribute("general", note);
                return "note/note_share";

            } else return "note/note_is_private";

        }
        return "note/note_not_found";
    }

    @GetMapping("/edit/{id}")
    public String editNotePage(@PathVariable("id") String stringId, Model model, Principal principal) {
        UUID id;
        try {
            id = UUID.fromString(stringId);
        } catch (IllegalArgumentException e) {
            return "note/note_not_found";
        }

        if (userRepository.findByEmail(principal.getName()).getId().equals(noteDaoService.findById(id).getUserId())) {
            model.addAttribute("note", noteDaoService.findById(id));
        } else return "note/not_your_note_page";

        return "note/edit";
    }

    @PatchMapping("/edit/")
    public String editNoteRequest(@ModelAttribute @Valid NoteModel noteModel) {
        noteDaoService.updateById(noteModel, noteModel.getId());

        return "redirect:/note/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteNote(@PathVariable UUID id) {
        noteDaoService.delete(id);

        return "redirect:/note/list";
    }
}