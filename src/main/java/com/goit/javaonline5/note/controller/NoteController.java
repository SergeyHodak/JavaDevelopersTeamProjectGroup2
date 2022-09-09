package com.goit.javaonline5.note.controller;

import com.goit.javaonline5.note.dao.abstraction.NoteDaoService;
import com.goit.javaonline5.note.model.NoteModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

        return "note/new";
    }

    @PostMapping("/new")
    public String addNewNote(@ModelAttribute NoteModel noteModel) {
        noteDaoService.save(noteModel);

        return "redirect:/";
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
