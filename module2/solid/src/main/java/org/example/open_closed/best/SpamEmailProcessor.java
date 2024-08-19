package org.example.open_closed.best;

import org.example.open_closed.EmailType;

public class SpamEmailProcessor implements EmailProcessor{
    @Override
    public void processEmail(String email) {
        System.out.println("Spam email processed: " + email);
    }

    @Override
    public EmailType getType() {
        return EmailType.SPAM;
    }
}
