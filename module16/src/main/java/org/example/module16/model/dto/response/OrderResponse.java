package org.example.module16.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrderResponse(
        LocalDate orderDate,
        String status,
        String version,
        BigDecimal totalPrice,
        Long customerId
) {
}