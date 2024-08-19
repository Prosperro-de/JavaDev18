package org.example.open_closed.bad;


import org.example.open_closed.EmailType;

public class EmailProcessor {
    public void processEmail(String email, EmailType type) {
        switch (type) {
            case MARKETING -> System.out.println("Processing marketing email to: " + email);
            case SUPPORT -> System.out.println("Processing support email to: " + email);
            case BIRTHDAY -> System.out.println("Processing birthday email to: " + email);
        }
    }
}
