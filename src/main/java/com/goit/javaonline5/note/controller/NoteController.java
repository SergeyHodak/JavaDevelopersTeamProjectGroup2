package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.enums.AccessType;
import com.goit.javaonline5.note.model.NoteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteDaoService noteDaoService;

    @GetMapping("/list")
    public String allNotesPage(Model model) {
        model.addAttribute("allNotes", noteDaoService.findAll());

        return "note/note_list";
    }

    @GetMapping("/create")
    public String newNotePage(Model model) {
        model.addAttribute("note", new NoteModel());
        model.addAttribute("access_types", AccessType.values());
        model.addAttribute("checked", AccessType.PRIVATE.toValue());

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

    @GetMapping("/{id}")
    public String certainNoteIdPage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/show";
    }

    @GetMapping("/{id}/edit")
    public String editNotePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editNoteRequest(@PathVariable("id") UUID id, @ModelAttribute NoteModel noteModel) {
        noteDaoService.updateById(noteModel, id);

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable UUID id) {
        noteDaoService.delete(id);

        return "redirect:/";
    }
}
