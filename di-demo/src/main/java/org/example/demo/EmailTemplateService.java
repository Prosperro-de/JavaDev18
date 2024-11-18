package org.example.demo;

import org.example.annotation.Component;

@Component
public class EmailTemplateService {
    private String template;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
