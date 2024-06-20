package com.progeksamensom2024backend.results;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResultService resultService;

    private ResultDTO result1, result2, result3;

    @BeforeEach
    void setUp() {
        result1 = new ResultDTO();
        result1.setId(1);
        result1.setParticipantId(1);
        result1.setDisciplineId(1);
        result1.setDate("2021-12-24");
        result1.setValue("10.0");

        result2 = new ResultDTO();
        result2.setId(2);
        result2.setParticipantId(2);
        result2.setDisciplineId(2);
        result1.setValue("10.0");

        result3 = new ResultDTO();
        result3.setId(3);
        result3.setParticipantId(3);
        result3.setDisciplineId(3);
        result1.setValue("10.0");
    }

    @Test
    void shouldReturnAllResults() throws Exception {
        List<ResultDTO> results = List.of(result1, result2, result3);
        when(resultService.getResults()).thenReturn(results);

        mockMvc.perform(get("/results"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnResultById() throws Exception {
        when(resultService.getResultById(1)).thenReturn(result1);

        mockMvc.perform(get("/results/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenResultNotFound() throws Exception {
        when(resultService.getResultById(1)).thenReturn(null);

        mockMvc.perform(get("/results/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateResult() throws Exception {
        when(resultService.createResult(any(ResultDTO.class))).thenReturn(result1);

        mockMvc.perform(post("/results")
                .contentType("application/json")
                .content("{\"id\":1,\"participantId\":1,\"disciplineId\":1,\"date\":\"2021-12-24\",\"value\":\"10.0\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateResult() throws Exception {
        when(resultService.editResult(anyInt(), any(ResultDTO.class))).thenReturn(result1);

        mockMvc.perform(post("/results/1")
                .contentType("application/json")
                .content("{\"id\":1,\"participantId\":1,\"disciplineId\":1,\"date\":\"2021-12-24\",\"value\":\"10.0\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotReturnUpdatingNonExistingResult() throws Exception {
        when(resultService.editResult(anyInt(), any(ResultDTO.class))).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(post("/results/1")
                .contentType("application/json")
                .content("{\"id\":1,\"participantId\":1,\"disciplineId\":1,\"date\":\"2021-12-24\",\"value\":\"10.0\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteResult() throws Exception {
        when(resultService.getResultById(anyInt())).thenReturn(result1);

        mockMvc.perform(delete("/results/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotDeleteNonExistingResult() throws Exception {
        when(resultService.getResultById(anyInt())).thenReturn(null);

        mockMvc.perform(delete("/results/1"))
                .andExpect(status().isNotFound());
    }
}