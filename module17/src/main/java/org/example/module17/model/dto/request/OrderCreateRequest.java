package org.example.module17.model.dto.request;

public record OrderCreateRequest(
        String status,
        String totalPrice,
        Long customerId
) {
}
