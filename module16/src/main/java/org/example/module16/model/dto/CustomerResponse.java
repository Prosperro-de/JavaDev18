package org.example.module16.model.dto;

import jakarta.persistence.Column;
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
