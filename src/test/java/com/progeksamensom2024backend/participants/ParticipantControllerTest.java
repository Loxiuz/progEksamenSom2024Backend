package com.progeksamensom2024backend.participants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParticipantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;

    private Participant participant1, participant2, participant3;

    @BeforeEach
    void setUp() {
        participant1 = new Participant();
        participant1.setId(1);
        participant1.setFullName("John Doe");

        participant2 = new Participant();
        participant2.setId(2);
        participant2.setFullName("Jane Doe");

        participant3 = new Participant();
        participant3.setId(3);
        participant3.setFullName("Jack Doe");

    }

    @Test
    void shouldReturnAllParticipants() throws Exception {
        List<Participant> participants = List.of(participant1, participant2, participant3);
        when(participantService.getParticipants()).thenReturn(participants);

        mockMvc.perform(get("/participants"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnParticipantById() throws Exception {
        when(participantService.getParticipantById(anyInt())).thenReturn(participant1);

        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenParticipantNotFound() throws Exception {
        when(participantService.getParticipantById(anyInt())).thenReturn(null);

        mockMvc.perform(get("/participants/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create participant")
    void shouldCreateParticipant() throws Exception {
        when(participantService.addParticipant(any(Participant.class))).thenReturn(participant1);

        mockMvc.perform(post("/participants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateParticipant() throws Exception {
        when(participantService.updateParticipant(anyInt(), any(Participant.class))).thenReturn(participant1);

        mockMvc.perform(put("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistingParticipant() throws Exception {
        when(participantService.updateParticipant(anyInt(), any(Participant.class))).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(put("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteParticipant() throws Exception {
        when(participantService.getParticipantById(anyInt())).thenReturn(participant1);

        mockMvc.perform(delete("/participants/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistingParticipant() throws Exception {
        when(participantService.getParticipantById(anyInt())).thenReturn(null);

        mockMvc.perform(delete("/participants/1"))
                .andExpect(status().isNotFound());
    }
}