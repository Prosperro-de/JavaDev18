package org.example.module19.model.dto;

public record ErrorResponse(String status, Integer errorCode, String message) {
}
