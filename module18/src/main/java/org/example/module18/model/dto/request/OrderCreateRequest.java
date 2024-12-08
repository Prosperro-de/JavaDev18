package org.example.module18.model.dto.request;

public record OrderCreateRequest(
        String status,
        String totalPrice,
        Long customerId
) {
}
