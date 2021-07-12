package webController;

import com.abernathyclinic.mediscreennotes.MediscreenNotes;
import com.abernathyclinic.mediscreennotes.controller.webController.NotesWebController;
import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import com.abernathyclinic.mediscreennotes.repository.PatientNoteRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotesWebController.class)
@ContextConfiguration(classes = {MediscreenNotes.class})
public class NotesWebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientNoteRepository patientNoteRepository;

    @Test
    void getAllPatientNotesTest() throws Exception {
        mockMvc.perform(get("/notes/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/list"));
    }

    @Test
    void getAddPatientNotesTest() throws Exception {
        mockMvc.perform(get("/notes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/add"));
    }

    @Test
    void getAddPatientNotesProcessTest() throws Exception {
        UUID uuid = UUID.fromString("01584892-eeb4-4e03-a13e-2a166034759e");
        PatientNote patientNote = new PatientNote(uuid, "notes");

        when(patientNoteRepository.save(patientNote)).thenReturn(patientNote);

        mockMvc.perform(post("/notes/add").flashAttr("note", patientNote))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/notes/list"));
    }

    @Test
    void getEditPatientNoteTest() throws Exception {
        UUID uuid = UUID.fromString("01584892-eeb4-4e03-a13e-2a166034759e");
        PatientNote patientNote = new PatientNote(uuid, "notes");
        String id = "1";

        when(patientNoteRepository.findById(id)).thenReturn(Optional.of(patientNote));

        mockMvc.perform(get("/notes/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("notes/edit"));
    }

    @Test
    void getEditPatientNoteProcessTest() throws Exception {
        UUID uuid = UUID.fromString("01584892-eeb4-4e03-a13e-2a166034759e");
        PatientNote patientNote = new PatientNote(uuid, "notes");
        String id = "1";

        when(patientNoteRepository.findById(id)).thenReturn(Optional.of(patientNote));
        when(patientNoteRepository.save(patientNote)).thenReturn(patientNote);

        mockMvc.perform(post("/notes/edit/" + id).flashAttr("note", patientNote))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/notes/list"));
    }
}
