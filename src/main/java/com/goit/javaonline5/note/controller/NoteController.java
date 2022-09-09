package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.enums.AccessType;
import com.goit.javaonline5.note.model.NoteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteDaoService noteDaoService;

    @GetMapping()
    public String allNotesPage(Model model) {
        model.addAttribute("allNotes", noteDaoService.findAll());

        return "note/index";
    }

    @GetMapping("/new")
    public String newNotePage(Model model) {
        model.addAttribute("note", new NoteModel());
        model.addAttribute("access_types", AccessType.values());

        return "note/new";
    }

    @PostMapping("/new")
    public String addNewNote(@ModelAttribute NoteModel noteModel) {
        noteDaoService.save(noteModel);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String certainNoteIdPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/show";
    }

    @GetMapping("/{id}/edit")
    public String editNotePage(@PathVariable("id") long id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editNoteRequest(@PathVariable("id") long id, @ModelAttribute NoteModel noteModel) {
        noteDaoService.updateById(noteModel, id);

        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable long id) {
        noteDaoService.delete(id);

        return "redirect:/";
    }
}
