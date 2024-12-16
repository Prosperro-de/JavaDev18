package org.example.module19.model.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerUpdateRequest(String firstName,
                             String lastName,
                             String telNumber,
                             String postCode,
                             LocalDate dateOfBirth,
                             Integer loyaltyPoints) {
}