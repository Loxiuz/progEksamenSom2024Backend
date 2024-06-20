package com.progeksamensom2024backend.participants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ParticipantServiceTest {

    @Mock
    private ParticipantRepository participantRepository;

    private ParticipantService participantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participantService = new ParticipantService(participantRepository);
    }

    @Test
    void updateParticipant() {
        Participant testParticipant = new Participant();
        testParticipant.setId(1);
        testParticipant.setFullName("Test Name");
        testParticipant.setEmail("test@email.com");

        when(participantRepository.findById(1)).thenReturn(Optional.of(testParticipant));
        when(participantRepository.save(testParticipant)).thenReturn(testParticipant);

        Participant updatedParticipant = new Participant();
        updatedParticipant.setFullName("Updated Name");
        updatedParticipant.setEmail("updated@email.com");

        Participant result = participantService.updateParticipant(1, updatedParticipant);

        assertEquals("Updated Name", result.getFullName());
        assertEquals("updated@email.com", result.getEmail());
    }

    @Test
    void testUpdateParticipantThrowsException() {
        Participant participant = new Participant();
        participant.setId(1);
        participant.setFullName("Test Name");

        int nonExistingId = 999;

        assertThrows(IllegalArgumentException.class, () -> {
            participantService.updateParticipant(nonExistingId, participant);
        });
    }
}