package org.example.module17.model.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerResponse(String firstName,
                               String lastName,
                               String email,
                               String telNumber,
                               String postCode,
                               LocalDate dateOfBirth,
                               Integer loyaltyPoints) {
}
