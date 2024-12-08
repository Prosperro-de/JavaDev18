package org.example.module18.model.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CustomerCreateRequest(String firstName,
                                    String lastName,
                                    String email,
                                    String telNumber,
                                    String postCode,
                                    LocalDate dateOfBirth,
                                    String password) {
}