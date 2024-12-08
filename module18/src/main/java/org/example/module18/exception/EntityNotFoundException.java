package org.example.module18.exception;

import org.example.module18.exception.DatabaseException;

public class EntityNotFoundException extends DatabaseException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
