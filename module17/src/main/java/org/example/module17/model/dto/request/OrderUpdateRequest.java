package org.example.module17.model.dto.request;

import java.math.BigDecimal;

public record OrderUpdateRequest(String status, BigDecimal totalPrice) {
}
