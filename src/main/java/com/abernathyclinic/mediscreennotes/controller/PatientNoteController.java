package com.abernathyclinic.mediscreennotes.controller;

import com.abernathyclinic.mediscreennotes.Exception.PatientNoteNotFoundException;
import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import com.abernathyclinic.mediscreennotes.repository.PatientNoteRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/notes")
public class PatientNoteController {
    @Autowired
    private PatientNoteRepository patientNoteRepository;

    @ApiOperation(value = "GET all Patient Notes in a list")
    @GetMapping("/getAll")
    public List<PatientNote> getAllPatientNotes() {
        return patientNoteRepository.findAll();
    }

    @ApiOperation(value = "GET Patient's Notes with this id")
    @GetMapping("/getPatientNoteById/{id}")
    public PatientNote getPatientNoteById(@PathVariable("id") String id) throws PatientNoteNotFoundException {
        return patientNoteRepository.findById(id).orElseThrow(() -> new PatientNoteNotFoundException("Patient's note for id : " + id + " could not be in the database"));
    }

    @ApiOperation(value = "GET Patient's Notes for Patient with this uuid")
    @GetMapping("/getPatientNoteByUuid/{uuid}")
    public List<PatientNote> getPatientNoteByUuid(@PathVariable("uuid") UUID uuid) throws PatientNoteNotFoundException {
        List<PatientNote> patientNoteList = patientNoteRepository.findPatientNoteByUuid(uuid);
        if (patientNoteList.isEmpty()) {
            throw new PatientNoteNotFoundException("Patient's note for uuid : " + uuid + " could not be in the database.");
        }
        return patientNoteList;
    }

    @ApiOperation(value = "ADD a new Patient's Note")
    @PostMapping("/addPatientNote")
    public String addPatientNote(@RequestBody PatientNote patientNote) {
        patientNoteRepository.save(patientNote);
        return "Patient's notes saved";
    }

    @ApiOperation(value = "EDIT Patient's Note with this id")
    @PutMapping("/editPatientNote/{id}")
    public String editPatientNote(@PathVariable("id") String id, @RequestBody PatientNote patientNote) {
        PatientNote patientNoteToUpdate = patientNoteRepository.findById(id).orElseThrow(() -> new PatientNoteNotFoundException("Patient's note with id : " + id + " could not be in the database."));
        patientNoteToUpdate.setNotes(patientNote.getNotes());
        patientNoteRepository.save(patientNoteToUpdate);
        return "Patient's notes updated";
    }
}
