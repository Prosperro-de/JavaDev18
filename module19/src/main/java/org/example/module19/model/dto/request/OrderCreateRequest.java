package org.example.module19.model.dto.request;

public record OrderCreateRequest(
        String status,
        String totalPrice,
        Long customerId
) {
}
