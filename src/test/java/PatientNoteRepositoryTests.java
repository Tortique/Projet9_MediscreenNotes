import com.abernathyclinic.mediscreennotes.MediscreenNotes;
import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import com.abernathyclinic.mediscreennotes.repository.PatientNoteRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenNotes.class})
@DataMongoTest
public class PatientNoteRepositoryTests {

    @Autowired
    private PatientNoteRepository patientNoteRepository;

    private final UUID uuid = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

    @Before
    void setUp() {
        PatientNote patientNote = new PatientNote(uuid, "note");
        patientNoteRepository.save(patientNote);
    }

    @Test
    void should_save_a_patient_note() {
        UUID uuid = UUID.randomUUID();
        PatientNote patientNote = new PatientNote(uuid, "new Note");
        patientNoteRepository.save(patientNote);
        String result = patientNoteRepository.findPatientNoteByUuid(uuid).get(0).getNotes();
        assertEquals("new Note", result);
    }
}
