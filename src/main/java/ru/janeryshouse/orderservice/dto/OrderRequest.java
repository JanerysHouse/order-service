package ru.janeryshouse.orderservice.dto;

import ru.janeryshouse.orderservice.model.Order;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link Order}
 */
public record OrderRequst(UUID id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}