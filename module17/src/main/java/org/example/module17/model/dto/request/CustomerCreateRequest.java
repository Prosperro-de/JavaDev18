package org.example.module17.model.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerCreateRequest(String firstName,
                                    String lastName,
                                    String email,
                                    String telNumber,
                                    String postCode,
                                    LocalDate dateOfBirth) {
}