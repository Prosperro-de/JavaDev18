package org.example.open_closed.best;

import org.example.open_closed.EmailType;

public class Demo {
    public static void main(String[] args) {
        EmailProcessorFactory factory = new EmailProcessorFactory();
        EmailProcessor supportEmailProcessor = factory.getProcessor(EmailType.SUPPORT);
        supportEmailProcessor.processEmail("mykola@gmail.com");

        EmailProcessor marketingEmailProcessor = factory.getProcessor(EmailType.MARKETING);
        marketingEmailProcessor.processEmail("mykola@gmail.com");

        EmailProcessor birthdayEmailProcessor = factory.getProcessor(EmailType.BIRTHDAY);
        birthdayEmailProcessor.processEmail("mykola@gmail.com");

        EmailProcessor spamEmailProcessor = factory.getProcessor(EmailType.SPAM);
        spamEmailProcessor.processEmail("mykola@gmail.com");
    }
}
