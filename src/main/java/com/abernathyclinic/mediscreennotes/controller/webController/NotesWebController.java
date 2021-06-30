package com.abernathyclinic.mediscreennotes.controller.webController;

import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import com.abernathyclinic.mediscreennotes.repository.PatientNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@Controller
@ApiIgnore
public class NotesWebController {

    @Autowired
    private PatientNoteRepository patientNoteRepository;

    @GetMapping("/notes/list")
    public ModelAndView getAllPatientNotes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("notes", patientNoteRepository.findAll());
        modelAndView.setViewName("notes/list");
        return modelAndView;
    }

    @GetMapping("/notes/add")
    public ModelAndView addPatientNotes(Model model) {
        PatientNote patientNote = new PatientNote();
        model.addAttribute("note", patientNote);
        return new ModelAndView("notes/add");
    }

    @PostMapping("/notes/add")
    public ModelAndView addPatientNotesProcess(@ModelAttribute("note") PatientNote patientNote) {
        ModelAndView modelAndView = new ModelAndView();
        patientNoteRepository.save(patientNote);
        modelAndView.setViewName("redirect:/notes/list");
        return modelAndView;
    }

    @GetMapping("/notes/edit/{id}")
    public ModelAndView editPatientNote(@PathVariable("id") String id, Model model) {
        Optional<PatientNote> patientNote = patientNoteRepository.findById(id);
        model.addAttribute("noteInfo", patientNote.get());
        model.addAttribute("note", patientNote.get());
        return new ModelAndView("notes/edit");
    }

    @PostMapping("/notes/edit/{id}")
    public ModelAndView editPatientNoteProcess(@PathVariable("id") String id, @ModelAttribute("note") PatientNote patientNote) {
        ModelAndView modelAndView = new ModelAndView();
        PatientNote patientNoteToUpdate = patientNoteRepository.findById(id).get();
        patientNoteToUpdate.setNotes(patientNote.getNotes());
        patientNoteRepository.save(patientNoteToUpdate);
        modelAndView.setViewName("redirect:/notes/list");
        return modelAndView;
    }
}
