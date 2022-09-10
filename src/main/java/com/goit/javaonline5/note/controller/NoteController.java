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
import java.util.ArrayList;
import java.util.List;
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

    @ModelAttribute("access_types")
    public List<AccessType> getCountries() {
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

    @GetMapping("/{id}/edit")
    public String editNotePage(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("general", noteDaoService.findById(id));

        return "note/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editNoteRequest(@PathVariable("id") UUID id, @ModelAttribute NoteModel noteModel) {
        noteDaoService.updateById(noteModel, id);

        return "redirect:/note/list";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable UUID id) {
        noteDaoService.delete(id);

        return "redirect:/note/list";
    }
}
