package org.example.demo;

import org.example.context.ApplicationContext;
import org.example.context.ApplicationContextImpl;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContextImpl("org.example.demo");
        EmailTemplateService templateService = context.getBean(EmailTemplateService.class);
        templateService.setTemplate("Birthday");

        EmailSenderService senderService = context.getBean(EmailSenderService.class);
        senderService.sendEmail();
    }
}
