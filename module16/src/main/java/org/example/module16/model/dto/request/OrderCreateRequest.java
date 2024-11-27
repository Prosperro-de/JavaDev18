package org.example.module16.model.dto.request;

public record OrderCreateRequest(
        String status,
        String totalPrice,
        Long customerId
) {
}
