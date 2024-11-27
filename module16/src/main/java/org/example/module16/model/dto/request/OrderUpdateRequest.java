package org.example.module16.model.dto.request;

import java.math.BigDecimal;

public record OrderUpdateRequest(String status, BigDecimal totalPrice) {
}
