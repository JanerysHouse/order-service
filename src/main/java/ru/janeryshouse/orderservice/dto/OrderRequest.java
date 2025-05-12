package ru.janeryshouse.orderservice.dto;

import ru.janeryshouse.orderservice.model.Order;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link Order}
 */
public record OrderRequest(UUID id, String orderNumber, String skuCode,
                           BigDecimal price, Integer quantity, UserDetails userDetails) {

    public record UserDetails(String email, String firstName, String lastName) {}
}