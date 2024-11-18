package org.example.demo;

import org.example.annotation.Autowired;
import org.example.annotation.Component;

@Component
public class EmailSenderService {
    @Autowired
    private EmailTemplateService emailTemplateService;

    public void sendEmail() {
        System.out.println("Sending email with template: " + emailTemplateService.getTemplate());
    }
}
