package org.example.open_closed.bad;

import org.example.open_closed.EmailType;

public class Demo {
    public static void main(String[] args) {
        EmailProcessor emailProcessor = new EmailProcessor();
        emailProcessor.processEmail("mykola@gmail.com", EmailType.MARKETING);
        emailProcessor.processEmail("mykola@gmail.com", EmailType.SUPPORT);
        emailProcessor.processEmail("mykola@gmail.com", EmailType.BIRTHDAY);
    }
}
