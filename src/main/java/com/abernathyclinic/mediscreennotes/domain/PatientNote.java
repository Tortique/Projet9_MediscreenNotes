package com.abernathyclinic.mediscreennotes.domain;


import org.springframework.data.annotation.Id;

import java.util.UUID;

public class PatientNote {

    @Id
    private String id;
    private UUID uuid;
    private String notes;

    public PatientNote() {
    }

    public PatientNote(UUID uuid, String notes) {
        this.uuid = uuid;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "PatientNote{" +
                "id='" + id + '\'' +
                ", uuid=" + uuid +
                ", notes='" + notes + '\'' +
                '}';
    }
}
