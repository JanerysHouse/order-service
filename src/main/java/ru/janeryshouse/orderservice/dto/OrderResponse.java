package ru.janeryshouse.orderservice.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link Order}
 */
public record OrderResponse(UUID id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}