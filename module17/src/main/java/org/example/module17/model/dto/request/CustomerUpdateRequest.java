package org.example.module17.model.dto.request;

import java.time.LocalDate;

public record CustomerUpdateRequest(String firstName,
                             String lastName,
                             String telNumber,
                             String postCode,
                             LocalDate dateOfBirth,
                             Integer loyaltyPoints) {
}