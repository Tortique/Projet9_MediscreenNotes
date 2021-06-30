import com.abernathyclinic.mediscreennotes.MediscreenNotes;
import com.abernathyclinic.mediscreennotes.controller.PatientNoteController;
import com.abernathyclinic.mediscreennotes.domain.PatientNote;
import com.abernathyclinic.mediscreennotes.repository.PatientNoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PatientNoteController.class)
@ContextConfiguration(classes = MediscreenNotes.class)
public class PatientNoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientNoteRepository patientNoteRepository;

    private final UUID uuid = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");

    @Test
    public void getAllPatientNotesTest() throws Exception {
        when(patientNoteRepository.findAll()).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(get("/api/getAll")).andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    public void getPatientNoteByUuidTest() throws Exception {
        UUID uuid = UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
        PatientNote patientNote = new PatientNote(uuid, "notes");
        List<PatientNote> patientNoteList = new ArrayList<>();
        patientNoteList.add(patientNote);
        when(patientNoteRepository.findPatientNoteByUuid(any(UUID.class))).thenReturn(patientNoteList);

        MvcResult mvcResult = mockMvc.perform(get("/api/getPatientNoteByUuid/3fa85f64-5717-4562-b3fc-2c963f66afa6"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
        verify(patientNoteRepository, times(1)).findPatientNoteByUuid(any(UUID.class));
    }

    @Test
    public void getPatientNoteByUuidThrownNotFoundExceptionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/getPatientNoteByUuid/3fa85f64-5717-4562-b3fc-2c963f66afa5"))
                .andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }

    @Test
    public void addPatientNoteTest() throws Exception {
        PatientNote patientNote = new PatientNote(uuid, "notes");

        when(patientNoteRepository.save(patientNote)).thenReturn(patientNote);

        MvcResult mvcResult = mockMvc.perform(post("/api/addPatientNote")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"notes\":\"notes\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Patient's notes saved", response.getContentAsString());
    }

    @Test
    public void editPatientNoteTest() throws Exception {
        PatientNote patientNote = new PatientNote(uuid, "notes");
        when(patientNoteRepository.findById("id")).thenReturn(java.util.Optional.of(patientNote));
        when(patientNoteRepository.save(patientNote)).thenReturn(patientNote);

        MvcResult mvcResult = mockMvc.perform(put("/api/editPatientNote/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"notes\":\"notes\"}"))
                .andDo(print())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("Patient's notes updated", response.getContentAsString());
    }

    @Test
    public void editPatientNoteThrownNotFoundExceptionTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/api/editPatientNote/id")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"uuid\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\"notes\":\"notes\"}"))
                .andDo(print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(404, status);
    }
}
