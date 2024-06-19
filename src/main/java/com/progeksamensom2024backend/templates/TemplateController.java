package com.progeksamensom2024backend.templates;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public List<TemplateEntity> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @GetMapping("/{id}")
    public TemplateEntity getTemplateById(@PathVariable int id) {
        return templateService.getTemplateById(id);
    }

    @PostMapping
    public TemplateEntity createTemplate(@RequestBody TemplateEntity template) {
        return templateService.createTemplate(template);
    }

    @PutMapping("/{id}")
    public TemplateEntity updateTemplate(@PathVariable int id, @RequestBody TemplateEntity template) {
        return templateService.updateTemplate(id, template);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable int id) {
        templateService.deleteTemplate(id);
    }
}
