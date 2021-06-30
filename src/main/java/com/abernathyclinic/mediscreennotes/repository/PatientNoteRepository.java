package com.abernathyclinic.mediscreennotes.repository;

import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {
    List<PatientNote> findPatientNoteByUuid(UUID uuid);
}
