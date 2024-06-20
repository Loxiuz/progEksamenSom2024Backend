package com.progeksamensom2024backend.results;

import com.progeksamensom2024backend.discipline.Discipline;
import com.progeksamensom2024backend.discipline.DisciplineRepository;
import com.progeksamensom2024backend.participants.Participant;
import com.progeksamensom2024backend.participants.ParticipantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResultServiceTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private DisciplineRepository disciplineRepository;

    @InjectMocks
    private ResultService resultService;

    private Participant participant;
    private Discipline discipline;
    private Result result;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        participant = new Participant();
        participant.setId(1);
        discipline = new Discipline();
        discipline.setId(1);

        result = new Result();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
    }

    @Test
    void shouldReturnResultWhenResultExists() {
        when(resultRepository.findById(anyInt())).thenReturn(Optional.of(result));

        ResultDTO resultDTO = resultService.getResultById(1);

        assertNotNull(resultDTO);
        verify(resultRepository, times(1)).findById(anyInt());
    }

    @Test
    void shouldThrowExceptionWhenResultDoesNotExist() {
        when(resultRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> resultService.getResultById(1));
        verify(resultRepository, times(1)).findById(anyInt());
    }

    @Test
    void shouldCreateResultWhenParticipantAndDisciplineExist() {
        when(participantRepository.findById(anyInt())).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(anyInt())).thenReturn(Optional.of(discipline));
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setParticipantId(participant.getId());
        resultDTO.setDisciplineId(discipline.getId());

        ResultDTO createdResultDTO = resultService.createResult(resultDTO);

        assertNotNull(createdResultDTO);
        assertEquals(1, createdResultDTO.getParticipantId());
        assertEquals(1, createdResultDTO.getDisciplineId());
        verify(participantRepository, times(1)).findById(anyInt());
        verify(disciplineRepository, times(1)).findById(anyInt());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void shouldUpdateResultWhenParticipantAndDisciplineExist() {
        when(participantRepository.findById(anyInt())).thenReturn(Optional.of(participant));
        when(disciplineRepository.findById(anyInt())).thenReturn(Optional.of(discipline));
        when(resultRepository.findById(anyInt())).thenReturn(Optional.of(result));
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setParticipantId(participant.getId());
        resultDTO.setDisciplineId(discipline.getId());

        ResultDTO updatedResultDTO = resultService.editResult(1, resultDTO);

        assertNotNull(updatedResultDTO);
        assertEquals(1, updatedResultDTO.getParticipantId());
        assertEquals(1, updatedResultDTO.getDisciplineId());
        verify(participantRepository, times(1)).findById(anyInt());
        verify(disciplineRepository, times(1)).findById(anyInt());
        verify(resultRepository, times(1)).findById(anyInt());
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    @Test
    void shouldThrowExceptionWhenResultDoesNotExistInEditResult() {
        when(resultRepository.findById(anyInt())).thenReturn(Optional.empty());

        ResultDTO resultDTO = new ResultDTO();

        assertThrows(IllegalArgumentException.class, () -> resultService.editResult(1, resultDTO));
        verify(resultRepository, times(1)).findById(anyInt());
    }
}