package org.example.open_closed.best;

import org.example.open_closed.EmailType;

public interface EmailProcessor {
    void processEmail(String email);
    EmailType getType();
}
