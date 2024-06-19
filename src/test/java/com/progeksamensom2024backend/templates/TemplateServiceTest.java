package com.progeksamensom2024backend.templates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class TemplateServiceTest {

    private TemplateService templateService;
    private TemplateRepository templateRepository;

    @BeforeEach
    void setUp() {
        templateRepository = Mockito.mock(TemplateRepository.class);
        templateService = new TemplateService(templateRepository);
    }

    @Test
    void shouldUpdateTemplate() {
        // Arrange
        TemplateEntity existingTemplate = new TemplateEntity();
        existingTemplate.setName("Existing Name");
        existingTemplate.setDescription("Existing Description");

        TemplateEntity newTemplate = new TemplateEntity();
        newTemplate.setName("New Name");
        newTemplate.setDescription("New Description");

        when(templateRepository.findById(eq(1))).thenReturn(Optional.of(existingTemplate));
        when(templateRepository.save(any(TemplateEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        TemplateEntity updatedTemplate = templateService.updateTemplate(1, newTemplate);

        // Assert
        assertNotNull(updatedTemplate);
        assertEquals("New Name", updatedTemplate.getName());
        assertEquals("New Description", updatedTemplate.getDescription());
    }
}