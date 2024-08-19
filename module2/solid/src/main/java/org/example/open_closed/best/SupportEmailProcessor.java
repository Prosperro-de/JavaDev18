package org.example.open_closed.best;

import org.example.open_closed.EmailType;

public class SupportEmailProcessor implements EmailProcessor {
    @Override
    public void processEmail(String email) {
        System.out.println("Support email processed: " + email);
    }

    @Override
    public EmailType getType() {
        return EmailType.SUPPORT;
    }
}
