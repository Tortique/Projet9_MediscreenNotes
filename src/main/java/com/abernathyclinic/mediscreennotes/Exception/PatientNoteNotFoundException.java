package com.abernathyclinic.mediscreennotes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNoteNotFoundException extends RuntimeException {

    public PatientNoteNotFoundException(String error) {
        super(error);
    }
}
