package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.enums.AccessType;
import com.goit.javaonline5.note.model.NoteModel;
import com.goit.javaonline5.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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
        model.addAttribute("allNotes", userRepository.findByEmail(principal.getName()).getNotes());

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
                             @RequestParam("access_type") String accessType
    ) {
        if (bindingResult.hasErrors()) return "note/new";

        noteModel.setAccessType(AccessType.valueOf(accessType));
        noteDaoService.save(noteModel);

        return "redirect:/note/list";
    }

    @GetMapping("/share/{id}")
    public String certainNoteIdPage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/note_share";
    }

    @GetMapping("/edit/{id}")
    public String editNotePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("note", noteDaoService.findById(id));

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