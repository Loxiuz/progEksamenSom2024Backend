package com.progeksamensom2024backend.templates;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<TemplateEntity> getAllTemplates() {
        return templateRepository.findAll();
    }

    public TemplateEntity getTemplateById(int id) {
        return templateRepository.findById(id).orElse(null);
    }

    public TemplateEntity createTemplate(TemplateEntity template) {
        return templateRepository.save(template);
    }

    public void deleteTemplate(int id) {
        templateRepository.deleteById(id);
    }

    // Should be copied and use (TemplateEntity original, TemplateEntity t) if using DTO
    public TemplateEntity updateTemplate(int id, TemplateEntity template) {
        TemplateEntity templateToUpdate = templateRepository.findById(id).orElse(null);
        if (templateToUpdate == null) {
            return null;
        }
        templateToUpdate.setName(template.getName());
        templateToUpdate.setDescription(template.getDescription());
        return templateRepository.save(templateToUpdate);
    }

}
