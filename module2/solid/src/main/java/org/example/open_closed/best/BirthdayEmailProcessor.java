package org.example.open_closed.best;

import org.example.open_closed.EmailType;

public class BirthdayEmailProcessor implements EmailProcessor{

    @Override
    public void processEmail(String email) {
        System.out.println("Birthday email processed: " + email);
    }

    @Override
    public EmailType getType() {
        return EmailType.BIRTHDAY;
    }
}
