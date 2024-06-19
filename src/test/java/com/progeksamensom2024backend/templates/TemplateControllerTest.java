package com.progeksamensom2024backend.templates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class TemplateControllerTest {

    private MockMvc mockMvc;
    private TemplateService templateService;

    @BeforeEach
    void setUp() {
        templateService = Mockito.mock(TemplateService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new TemplateController(templateService)).build();
    }

    @Test
    void shouldReturnAllTemplates() throws Exception {
        given(templateService.getAllTemplates()).willReturn(Arrays.asList(new TemplateEntity(), new TemplateEntity()));

        mockMvc.perform(get("/templates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldReturnTemplateById() throws Exception {
        given(templateService.getTemplateById(1)).willReturn(new TemplateEntity());

        mockMvc.perform(get("/templates/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void shouldCreateTemplate() throws Exception {
        given(templateService.createTemplate(Mockito.any(TemplateEntity.class))).willReturn(new TemplateEntity());

        mockMvc.perform(post("/templates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void shouldUpdateTemplate() throws Exception {
        given(templateService.updateTemplate(Mockito.eq(1),
                Mockito.any(TemplateEntity.class))).willReturn(new TemplateEntity());

        mockMvc.perform(put("/templates/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void shouldDeleteTemplate() throws Exception {
        mockMvc.perform(delete("/templates/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}